package view;

import javax.swing.*;
import java.awt.*;

public class FernseherView extends JFrame {

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
    JTextField txtNennlesitung = new JTextField();

    JLabel lblWhiteSpace = new JLabel();

    JButton btnAdd = new JButton("Hinzufügen");

    JDialog dialog = new JDialog();

    public void showAddDialog() {
        dialog.setSize(400, 500);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new GridLayout(0, 2, 5, 5));

        addComponentsToDialog();

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
        dialog.add(txtNennlesitung);

        dialog.add(lblWhiteSpace);

        dialog.add(btnAdd);

    }
}
