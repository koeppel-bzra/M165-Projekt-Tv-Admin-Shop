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



    public void showAddDialog() {
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

        initCombobox();

        dialog.setVisible(true);

        btnAction.addActionListener(e -> {
            addBestellung();
        });
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
                txtBestellNummer.getText(),
                LocalDate.ofInstant(
                        ((Date) datePicker.getModel().getValue()).toInstant(),
                        ZoneId.systemDefault()
                ),
                (Kunde) cmbKunde.getSelectedItem(),
                bestellpositionen
        );

        bestellungenController.addBestellung(bestellung);
        view.refreshBestellungenList();
        dialog.dispose();
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


}
