package view;

import com.sun.tools.javac.Main;
import controller.FernseherController;
import model.Fernseher;

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
    JTextField txtDisplaytechnologie = new JTextField();
    JLabel lblBildschirmaufloesung = new JLabel("Bildschirmauflösung");
    JTextField txtBildschirmaufloesung = new JTextField();
    JLabel lblBildwiederholfrequenz = new JLabel("Bildwiederholfrequenz");
    JTextField txtBildwederholfrequenz = new JTextField();
    JLabel lblGewicht = new JLabel("Gewicht");
    JTextField txtGewicht = new JTextField();
    JLabel lblReleasedatum = new JLabel("Releasedatum");
    JTextField txtReleasedatum = new JTextField();
    JLabel lblPixelaufloesung = new JLabel("Pixelauflösung");
    JTextField txtPixelaufloesung = new JTextField();
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
        dialog.add(txtDisplaytechnologie);
        dialog.add(lblBildschirmaufloesung);
        dialog.add(txtBildschirmaufloesung);
        dialog.add(lblBildwiederholfrequenz);
        dialog.add(txtBildwederholfrequenz);
        dialog.add(lblGewicht);
        dialog.add(txtGewicht);
        dialog.add(lblReleasedatum);
        dialog.add(txtReleasedatum);
        dialog.add(lblPixelaufloesung);
        dialog.add(txtPixelaufloesung);
        dialog.add(lblNennleistung);
        dialog.add(txtNennleistung);

        dialog.add(lblWhiteSpace);

        dialog.add(btnAdd);
    }

    public void addFernseherUI() {
        btnAdd.addActionListener(e -> {

            String marke = txtMarke.getText();
            String modell = txtModell.getText();
            double preis = Double.parseDouble(txtPreis.getText());
            int bildschirmdiagonale = Integer.parseInt(txtBildschirmdiagonale.getText());
            String displayTechnologie = txtDisplaytechnologie.getText();
            String bildschirmAufloesung = txtBildschirmaufloesung.getText();
            int bildwiederholfrequenz = Integer.parseInt(txtBildwederholfrequenz.getText());
            double gewicht = Double.parseDouble(txtGewicht.getText());
            String releasedatumString = txtReleasedatum.getText();

            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

            Date releaseDatum = null;

            try {
                releaseDatum = formatter.parse(releasedatumString);
            } catch (ParseException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Bitte ein korrektes Datum eingeben (TT.MM.JJJJ)");
                dialog.dispose();
            }

            String pixelaufloesung = txtPixelaufloesung.getText();
            int nennleistung = Integer.parseInt(txtNennleistung.getText());


            Fernseher f = new Fernseher(marke, modell, preis, bildschirmdiagonale, displayTechnologie,
                                        bildschirmAufloesung, bildwiederholfrequenz,
                                        gewicht, releaseDatum, pixelaufloesung, nennleistung);

            controller.addFernseher(f);

            view.listModel.addElement(f.getMarke() + " - " + f.getModell());

            dialog.dispose();
        });
    }
}
