package view;

import controller.BestellungenController;
import controller.FernseherController;
import controller.KundenController;
import model.*;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class BestellungView {

    // Referenzen
    private BestellungenController bestellungenController;
    private KundenController kundenController;
    private FernseherController fernseherController;
    private MainView view;

    // Komponente
    private JLabel lblBestellNummer = new JLabel("Bestellnummer");
    private JTextField txtBestellNummer = new JTextField();

    private JLabel lblBestellDatum = new JLabel("Releasedatum");
    private UtilDateModel dateModel = new UtilDateModel();
    private JDatePickerImpl datePicker;

    private JLabel lblKunde = new JLabel("Kunde");
    private JComboBox<Kunde> cmbKunde = new JComboBox<>();

    private JLabel lblFernseher = new JLabel("Fernseher");
    private JComboBox<Fernseher> cmbFernseher = new JComboBox<>();

    private JLabel lblMenge = new JLabel("Menge");
    private JSpinner spnMenge = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));

    private JLabel lblWhiteSpace = new JLabel();
    private JButton btnAction = new JButton("Hinzufügen");
    private JDialog dialog = new JDialog();

    public BestellungView(MainView view, BestellungenController bestellungenController, FernseherController fernseherController, KundenController kundenController) {
        this.view = view;
        this.bestellungenController = bestellungenController;
        this.kundenController = kundenController;
        this.fernseherController = fernseherController;

        dateModel.setSelected(true);
        Properties p = new Properties();
        p.put("text.today", "Heute");
        p.put("text.month", "Monat");
        p.put("text.year", "Jahr");
        JDatePanelImpl datePanel = new JDatePanelImpl(dateModel, p);
        datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
    }


    // Bestellungen adden
    public void showAddDialog() {
        btnAction.setText("Hinzufügen");

        for (ActionListener al : btnAction.getActionListeners()) {
            btnAction.removeActionListener(al);
        }

        btnAction.addActionListener(e -> addBestellung());

        clearFields();
        initCombobox();
        setupDialog();
    }

    public void addBestellung() {
        List<Bestellposition> bestellpositionen = new ArrayList<>();

        Bestellposition bestellposition = new Bestellposition();
        bestellposition.setFernseher((Fernseher) cmbFernseher.getSelectedItem());
        bestellposition.setStueckzahl((Integer) spnMenge.getValue());

        Fernseher f = (Fernseher) cmbFernseher.getSelectedItem();
        bestellposition.setEinzelpreis(f.getPreis());

        bestellpositionen.add(bestellposition);

        Bestellung bestellung = new Bestellung(
                Integer.parseInt(txtBestellNummer.getText()),
                LocalDate.ofInstant(
                        ((Date) datePicker.getModel().getValue()).toInstant(),
                        ZoneId.systemDefault()
                ),
                (Kunde) cmbKunde.getSelectedItem(),
                bestellpositionen
        );

        bestellungenController.addBestellung(bestellung);
        dialog.dispose();
        view.refreshBestellungenList();
    }


    // Bestellungen updaten
    public void showUpdateDialog() {
        String selectedString = view.bestellungenList.getSelectedValue();
        if (selectedString == null) {
            JOptionPane.showMessageDialog(view, "Keine Bestellunge ausgewählt");
            return;
        }

            Bestellung bToUpdate = bestellungenController.getAllBestellungen().stream()
                    .filter(b -> (b.getBestellnummer() + " - " + b.getKunde().getNachname() + " " + b.getKunde().getVorname())
                            .equals(selectedString)).findFirst().orElse(null);


        if (bToUpdate == null) {
            JOptionPane.showMessageDialog(view, "Bestellung nicht gefunden");
            return;
        }

        initCombobox();
        fillFields(bToUpdate);
        setupDialog();

        btnAction.setText("Aktualisieren");

        for (ActionListener al : btnAction.getActionListeners()) {
            btnAction.removeActionListener(al);
        }

        btnAction.addActionListener(e -> {
            updateBestellung(bToUpdate);
        });


    }

    public void updateBestellung(Bestellung bestellung) {
        bestellung.setBestellnummer(Integer.parseInt(txtBestellNummer.getText()));
        bestellung.setBestellDatum(
                LocalDate.ofInstant(
                        ((Date) datePicker.getModel().getValue()).toInstant(),
                        ZoneId.systemDefault()
                )
        );
        List<Bestellposition> bestellpositionen = bestellung.getBestellpositionen();
        bestellung.setKunde((Kunde) cmbKunde.getSelectedItem());

        for (Bestellposition pos : bestellpositionen) {
            pos.setStueckzahl((Integer) spnMenge.getValue());
            pos.setFernseher((Fernseher) cmbFernseher.getSelectedItem());
        }

        bestellungenController.updateBestellung(bestellung);
        view.refreshBestellungenList();

        dialog.dispose();
    }


    // Bestellungen löschen
    public void deleteBestellung() {
        String selectedString = view.bestellungenList.getSelectedValue();

        if (selectedString == null) {
            JOptionPane.showMessageDialog(view, "Keine Bestellung ausgewählt");
        }

        Bestellung selected = new Bestellung();
        for (Bestellung b : bestellungenController.getAllBestellungen()) {
            if ((b.getBestellnummer() + " - " + b.getKunde().getNachname() + " " + b.getKunde().getVorname()).equals(selectedString)) {
                selected = b;
                break;
            }
        }

        if (selected != null) {

            int result = JOptionPane.showConfirmDialog(
                    view,
                    "Möchtest du diese Bestellung wirklich löschen?",
                    "Löschen bestätigen",
                    JOptionPane.YES_NO_OPTION
            );

            if (result == JOptionPane.YES_OPTION) {
                bestellungenController.deleteBestellung(selected);
                view.refreshBestellungenList();
                view.updateBestellungenDetails();
            }
        }
    }





    public void initCombobox() {
        cmbFernseher.removeAllItems();
        for (Fernseher f : fernseherController.getAllFernseher()) {
            cmbFernseher.addItem(f);
        }

        cmbKunde.removeAllItems();
        for (Kunde k : kundenController.getAllKunden()) {
            cmbKunde.addItem(k);
        }
    }

    private void fillFields(Bestellung b) {

        List<Bestellposition> bestellpositionen = b.getBestellpositionen();

        txtBestellNummer.setText(String.valueOf(b.getBestellnummer()));
        dateModel.setValue(
                Date.from(
                        b.getBestellDatum()
                                .atStartOfDay(ZoneId.systemDefault())
                                .toInstant()
                )
        );
        cmbKunde.setSelectedItem(b.getKunde());

        Bestellposition pos = bestellpositionen.get(0);
        spnMenge.setValue(pos.getStueckzahl());
        cmbFernseher.setSelectedItem(pos.getFernseher());

    }

    private void setupDialog() {
        dialog.getContentPane().removeAll();
        dialog.setSize(400, 250);
        dialog.setLocationRelativeTo(view);
        dialog.setLayout(new GridLayout(0, 2, 5, 5));

        dialog.add(lblBestellNummer);
        dialog.add(txtBestellNummer);
        dialog.add(lblBestellDatum);
        dialog.add(datePicker);
        dialog.add(lblKunde);
        dialog.add(cmbKunde);
        dialog.add(lblFernseher);
        dialog.add(cmbFernseher);
        dialog.add(lblMenge);
        dialog.add(spnMenge);
        dialog.add(lblWhiteSpace);
        dialog.add(btnAction);

        dialog.setVisible(true);
    }

    private void clearFields() {
        txtBestellNummer.setText("");
        spnMenge.setValue(1);
        dateModel.setValue(new Date());
    }



}
