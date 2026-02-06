package view;

import com.sun.tools.javac.Main;
import controller.FernseherController;
import model.Bildschirmaufloesung;
import model.DisplayTechnologie;
import model.Fernseher;
import model.Pixelaufloesung;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FernseherView extends JFrame {

    FernseherController controller;
    MainView view; // Referenz auf MainView

    public FernseherView(MainView view, FernseherController controller) {
        this.view = view;
        this.controller = controller;
    }

    // Instanzierung der Komponente
    JLabel lblMarke = new JLabel("Marke");
    JTextField txtMarke = new JTextField();
    JLabel lblModell = new JLabel("Modell");
    JTextField txtModell = new JTextField();
    JLabel lblPreis = new JLabel("Preis");
    JTextField txtPreis = new JTextField();
    JLabel lblBildschirmdiagonale = new JLabel("Bildschirmdiagonale");
    JTextField txtBildschirmdiagonale = new JTextField();
    JLabel lblDisplaytechnologie = new JLabel("Displaytechnologie");
    JComboBox<DisplayTechnologie> cmbDisplaytechnologie = new JComboBox<>(DisplayTechnologie.values());
    JLabel lblBildschirmaufloesung = new JLabel("Bildschirmauflösung");
    JComboBox<Bildschirmaufloesung> cmbBildschirmaufloesung = new JComboBox<>(Bildschirmaufloesung.values());
    JLabel lblBildwiederholfrequenz = new JLabel("Bildwiederholfrequenz");
    JTextField txtBildwederholfrequenz = new JTextField();
    JLabel lblGewicht = new JLabel("Gewicht");
    JTextField txtGewicht = new JTextField();
    JLabel lblReleasedatum = new JLabel("Releasedatum");
    JTextField txtReleasedatum = new JTextField();
    JLabel lblPixelaufloesung = new JLabel("Pixelauflösung");
    JComboBox<Pixelaufloesung> cmbPixelaufloesung = new JComboBox<>(Pixelaufloesung.values());
    JLabel lblNennleistung = new JLabel("Nennleistung");
    JTextField txtNennleistung = new JTextField();



    JLabel lblWhiteSpace = new JLabel();

    JButton btnAdd = new JButton("Hinzufügen");

    JDialog dialog = new JDialog();

    public void showAddDialog() {
        dialog.setSize(400, 500);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new GridLayout(0, 2, 5, 5));

        addComponentsToDialog();
        addFernseherUI();



        dialog.setVisible(true);
    }

    public void addComponentsToDialog() {
        // Label und Textfields
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

        dialog.add(btnAdd);
    }

    public void addFernseherUI() {
        btnAdd.addActionListener(e -> {

            try {
                String marke = txtMarke.getText();
                String modell = txtModell.getText();
                double preis = Double.parseDouble(txtPreis.getText());
                int bildschirmdiagonale = Integer.parseInt(txtBildschirmdiagonale.getText());

                DisplayTechnologie displayTechnologie = (DisplayTechnologie) cmbDisplaytechnologie.getSelectedItem();
                Bildschirmaufloesung bildschirmAufloesung = (Bildschirmaufloesung) cmbBildschirmaufloesung.getSelectedItem();
                int bildwiederholfrequenz = Integer.parseInt(txtBildwederholfrequenz.getText());
                double gewicht = Double.parseDouble(txtGewicht.getText());

                String releasedatumString = txtReleasedatum.getText();
                SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
                Date releaseDatum = formatter.parse(releasedatumString);

                Pixelaufloesung pixelAufloesung = (Pixelaufloesung) cmbPixelaufloesung.getSelectedItem();
                int nennleistung = Integer.parseInt(txtNennleistung.getText());

                // Fernseher-Objekt erstellen
                Fernseher f = new Fernseher(marke, modell, preis, bildschirmdiagonale, displayTechnologie,
                        bildschirmAufloesung, bildwiederholfrequenz,
                        gewicht, releaseDatum, pixelAufloesung, nennleistung);

                // Zur DB hinzufügen
                controller.addFernseher(f);

                // In die List anzeigen
                view.listModel.addElement(f.getMarke() + " - " + f.getModell());

                // Optional: Dialog schließen
                dialog.dispose();

            } catch (NumberFormatException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(dialog, "Bitte gültige Werte eingeben");

            } catch (ParseException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(dialog, "Bitte ein korrektes Datum eingeben (TT.MM.JJJJ)");
            }

        });
    }


    private DisplayTechnologie getDisplayTechnologieSafe(Document doc) {
        String value = doc.getString("displayTechnologie");
        if (value == null) return DisplayTechnologie.LED; // Standardwert
        return DisplayTechnologie.valueOf(value.toUpperCase());
    }

    private Bildschirmaufloesung getBildschirmAufloesungSafe(Document doc) {
        String value = doc.getString("bildschirmAufloesung");
        if (value == null) return Bildschirmaufloesung.HD;
        return Bildschirmaufloesung.valueOf(value.toUpperCase());
    }

    private Pixelaufloesung getPixelAufloesungSafe(Document doc) {
        String value = doc.getString("pixelaufloesung");
        if (value == null) return Pixelaufloesung.P720;
        return Pixelaufloesung.valueOf(value.toUpperCase());
    }

}
