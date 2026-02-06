package view;

import controller.FernseherController;
import model.Bildschirmaufloesung;
import model.DisplayTechnologie;
import model.Fernseher;
import model.Pixelaufloesung;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionListener;

public class FernseherView extends JFrame {

    private FernseherController controller;
    private MainView view;

    // Komponenten
    private JLabel lblMarke = new JLabel("Marke");
    private JTextField txtMarke = new JTextField();
    private JLabel lblModell = new JLabel("Modell");
    private JTextField txtModell = new JTextField();
    private JLabel lblPreis = new JLabel("Preis");
    private JTextField txtPreis = new JTextField();
    private JLabel lblBildschirmdiagonale = new JLabel("Bildschirmdiagonale");
    private JTextField txtBildschirmdiagonale = new JTextField();
    private JLabel lblDisplaytechnologie = new JLabel("Displaytechnologie");
    private JComboBox<DisplayTechnologie> cmbDisplaytechnologie = new JComboBox<>(DisplayTechnologie.values());
    private JLabel lblBildschirmaufloesung = new JLabel("Bildschirmauflösung");
    private JComboBox<Bildschirmaufloesung> cmbBildschirmaufloesung = new JComboBox<>(Bildschirmaufloesung.values());
    private JLabel lblBildwiederholfrequenz = new JLabel("Bildwiederholfrequenz");
    private JTextField txtBildwederholfrequenz = new JTextField();
    private JLabel lblGewicht = new JLabel("Gewicht [kg]");
    private JTextField txtGewicht = new JTextField();
    private JLabel lblReleasedatum = new JLabel("Releasedatum");
    private JTextField txtReleasedatum = new JTextField();
    private JLabel lblPixelaufloesung = new JLabel("Pixelauflösung");
    private JComboBox<Pixelaufloesung> cmbPixelaufloesung = new JComboBox<>(Pixelaufloesung.values());
    private JLabel lblNennleistung = new JLabel("Nennleistung [w]");
    private JTextField txtNennleistung = new JTextField();

    private JLabel lblWhiteSpace = new JLabel();
    private JButton btnAction = new JButton("Hinzufügen");
    private JDialog dialog = new JDialog();

    public FernseherView(MainView view, FernseherController controller) {
        this.view = view;
        this.controller = controller;
    }


    // Dialog für das Adden
    public void showAddDialog() {
        clearFields();
        btnAction.setText("Hinzufügen");

        for (ActionListener al : btnAction.getActionListeners()) {
            btnAction.removeActionListener(al);
        }
        btnAction.addActionListener(e -> addFernseher());

        setupDialog();
    }

    private void addFernseher() {
        try {
            Fernseher f = buildFernseherFromFields();
            controller.addFernseher(f);
            view.refreshList();
            dialog.dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(dialog, "Bitte gültige Werte eingeben");
        }
    }

    // Dialog für das Updaten
    public void showUpdateDialog() {
        String selectedString = view.list.getSelectedValue();
        if (selectedString == null) {
            JOptionPane.showMessageDialog(view, "Kein Fernseher ausgewählt");
            return;
        }

        Fernseher fToUpdate = controller.getAllFernseher().stream().filter(f -> (f.getMarke() + " - " + f.getModell()).equals(selectedString)).findFirst().orElse(null);

        if (fToUpdate == null) {
            JOptionPane.showMessageDialog(view, "Fernseher nicht gefunden");
            return;
        }

        fillFields(fToUpdate);

        btnAction.setText("Aktualisieren");

        for (ActionListener al : btnAction.getActionListeners()) {
            btnAction.removeActionListener(al);
        }
        btnAction.addActionListener(e -> updateFernseherUI(fToUpdate));

        setupDialog();
    }

    public void updateFernseherUI(Fernseher f) {
        try {
            f.setMarke(txtMarke.getText());
            f.setModell(txtModell.getText());
            f.setPreis(Double.parseDouble(txtPreis.getText()));
            f.setBildschirmdiagonale(Integer.parseInt(txtBildschirmdiagonale.getText()));
            f.setDisplayTechnologie((DisplayTechnologie) cmbDisplaytechnologie.getSelectedItem());
            f.setBildschirmAufloesung((Bildschirmaufloesung) cmbBildschirmaufloesung.getSelectedItem());
            f.setBildwiederholFrequenz(Integer.parseInt(txtBildwederholfrequenz.getText()));
            f.setGewicht(Double.parseDouble(txtGewicht.getText()));
            f.setReleaseDatum(LocalDate.parse(txtReleasedatum.getText(), DateTimeFormatter.ofPattern("dd.MM.yyyy")));
            f.setPixelaufloesung((Pixelaufloesung) cmbPixelaufloesung.getSelectedItem());
            f.setNennleistung(Integer.parseInt(txtNennleistung.getText()));

            controller.updateFernseher(f);

            view.refreshList();
            dialog.dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(dialog, "Ungültige Eingabe");
        }
    }

    // Löschen
    public void deleteFernseherUI() {
        String selectedString = view.list.getSelectedValue();
        if (selectedString == null) {
            JOptionPane.showMessageDialog(view, "Kein Fernseher ausgewählt");
            return;
        }

        Fernseher selected = null;
        for (Fernseher f : controller.getAllFernseher()) {
            if ((f.getMarke() + " - " + f.getModell()).equals(selectedString)) {
                selected = f;
                break;
            }
        }

        if (selected != null) {
            controller.deleteFernseher(selected, selected.getFernseherId());
            view.refreshList();
            view.ClearDetailList();
        }
    }

    // Generalisierte Methode für Adden und Updaten
    private void setupDialog() {
        dialog.getContentPane().removeAll();
        dialog.setSize(400, 500);
        dialog.setLocationRelativeTo(view);
        dialog.setLayout(new GridLayout(0, 2, 5, 5));

        dialog.add(lblMarke);
        dialog.add(txtMarke);
        dialog.add(lblModell);
        dialog.add(txtModell);
        dialog.add(lblPreis);
        dialog.add(txtPreis);
        dialog.add(lblBildschirmdiagonale);
        dialog.add(txtBildschirmdiagonale);
        dialog.add(lblDisplaytechnologie);
        dialog.add(cmbDisplaytechnologie);
        dialog.add(lblBildschirmaufloesung);
        dialog.add(cmbBildschirmaufloesung);
        dialog.add(lblBildwiederholfrequenz);
        dialog.add(txtBildwederholfrequenz);
        dialog.add(lblGewicht);
        dialog.add(txtGewicht);
        dialog.add(lblReleasedatum);
        dialog.add(txtReleasedatum);
        dialog.add(lblPixelaufloesung);
        dialog.add(cmbPixelaufloesung);
        dialog.add(lblNennleistung);
        dialog.add(txtNennleistung);
        dialog.add(lblWhiteSpace);
        dialog.add(btnAction);

        dialog.setVisible(true);
    }

    private void clearFields() {
        txtMarke.setText("");
        txtModell.setText("");
        txtPreis.setText("");
        txtBildschirmdiagonale.setText("");
        txtBildwederholfrequenz.setText("");
        txtGewicht.setText("");
        txtReleasedatum.setText("");
        txtNennleistung.setText("");
    }

    private void fillFields(Fernseher f) {
        txtMarke.setText(f.getMarke());
        txtModell.setText(f.getModell());
        txtPreis.setText(String.valueOf(f.getPreis()));
        txtBildschirmdiagonale.setText(String.valueOf(f.getBildschirmdiagonale()));
        cmbDisplaytechnologie.setSelectedItem(f.getDisplayTechnologie());
        cmbBildschirmaufloesung.setSelectedItem(f.getBildschirmAufloesung());
        txtBildwederholfrequenz.setText(String.valueOf(f.getBildwiederholFrequenz()));
        txtGewicht.setText(String.valueOf(f.getGewicht()));
        txtReleasedatum.setText(f.getReleaseDatum().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        cmbPixelaufloesung.setSelectedItem(f.getPixelaufloesung());
        txtNennleistung.setText(String.valueOf(f.getNennleistung()));
    }

    private Fernseher buildFernseherFromFields() throws NumberFormatException {
        return new Fernseher(
                txtMarke.getText(),
                txtModell.getText(),
                Double.parseDouble(txtPreis.getText()),
                Integer.parseInt(txtBildschirmdiagonale.getText()),
                (DisplayTechnologie) cmbDisplaytechnologie.getSelectedItem(),
                (Bildschirmaufloesung) cmbBildschirmaufloesung.getSelectedItem(),
                Integer.parseInt(txtBildwederholfrequenz.getText()),
                Double.parseDouble(txtGewicht.getText()),
                LocalDate.parse(txtReleasedatum.getText(), DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                (Pixelaufloesung) cmbPixelaufloesung.getSelectedItem(),
                Integer.parseInt(txtNennleistung.getText())
        );
    }
}
