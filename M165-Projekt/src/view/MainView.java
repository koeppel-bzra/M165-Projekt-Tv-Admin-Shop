package view;

import controller.FernseherController;
import model.Fernseher;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    FernseherController controller = new FernseherController();
    FernseherView fernseherView = new FernseherView(this, controller);

    // Panels
    JPanel panel = new JPanel(new BorderLayout());
    JPanel navPanel = new JPanel(new GridLayout(6, 1, 5, 5));
    JPanel midPanel = new JPanel();
    JPanel detailPanel = new JPanel();

    // Liste
    DefaultListModel<String> listModel = new DefaultListModel<>();
    JList<String> list = new JList<>(listModel);

    // Detail Labels Bildschirm
    JLabel lblMarke = new JLabel("Marke: ");
    JLabel lblModell = new JLabel("Modell: ");
    JLabel lblPreis = new JLabel("Preis: ");
    JLabel lblBildschirmdiagonale = new JLabel("Bildschirmdiagonale: ");
    JLabel lblDisplaytechnologie = new JLabel("Displaytechnologie: ");
    JLabel lblBildschirmaufloesung = new JLabel("Bildschirmauflösung: ");
    JLabel lblBildwiederholfrequenz = new JLabel("Bildwiederholfrequenz: ");
    JLabel lblGewicht = new JLabel("Gewicht: ");
    JLabel lblReleasedatum = new JLabel("Releasedatum: ");
    JLabel lblPixelaufloesung = new JLabel("Pixelauflösung: ");
    JLabel lblNennleistung = new JLabel("Nennleistung: ");

    // Buttons
    JButton btnAddFernseher = new JButton("Fernseher hinzufügen");

    public MainView() {
        setSize(1000, 600);
        setTitle("TV-Verwaltung");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel Aufbau
        panel.add(navPanel, BorderLayout.WEST);
        panel.add(midPanel, BorderLayout.CENTER);
        panel.add(detailPanel, BorderLayout.EAST);
        add(panel);

        // Methoden aufrufen
        Navigation();
        MidPanel();
        DetailPanel();
        ButtonActions();

        setVisible(true);
    }



    public void Navigation() {
        navPanel.add(btnAddFernseher);
        navPanel.setPreferredSize(new Dimension(150, 0));
    }


    public void MidPanel() {
        midPanel.setLayout(new BorderLayout());
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        controller.getAllFernseher();

        for (Fernseher f : controller.getAllFernseher()) {
            listModel.addElement(f.getMarke() + " - " + f.getModell());
        }

        JScrollPane scrollPane = new JScrollPane(list);
        midPanel.add(scrollPane, BorderLayout.CENTER);


        list.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                DetailList();
            }
        });
    }


    public void DetailPanel() {
        detailPanel.setLayout(new GridLayout(0,1,5,5));
        detailPanel.add(lblMarke);
        detailPanel.add(lblModell);
        detailPanel.add(lblPreis);
        detailPanel.add(lblBildschirmdiagonale);
        detailPanel.add(lblDisplaytechnologie);
        detailPanel.add(lblBildschirmaufloesung);
        detailPanel.add(lblBildwiederholfrequenz);
        detailPanel.add(lblGewicht);
        detailPanel.add(lblReleasedatum);
        detailPanel.add(lblPixelaufloesung);
        detailPanel.add(lblNennleistung);
        detailPanel.setPreferredSize(new Dimension(300, 0));
    }

    public void DetailList() {
        int index = list.getSelectedIndex();
        if (index >= 0) {
            Fernseher f = controller.getAllFernseher().get(index);

            lblMarke.setText("Marke: " + f.getMarke());
            lblModell.setText("Modell: " + f.getModell());
            lblPreis.setText("Preis: " + f.getPreis() + " CHF");
            lblBildschirmdiagonale.setText("Bildschirmdiagonale: " + f.getBildschirmdiagonale());
            lblDisplaytechnologie.setText("Displaytechnologie: " + f.getDisplayTechnologie());
            lblBildschirmaufloesung.setText("Bildschirmauflösung: " + f.getBildschirmAufloesung());
            lblBildwiederholfrequenz.setText("Bildwiederholfrequenz: " + f.getBildwiederholFrequenz());
            lblGewicht.setText("Gewicht: " + f.getGewicht());
            lblReleasedatum.setText("Releasedatum: " + f.getReleaseDatum());
            lblPixelaufloesung.setText("Pixelauflösung: " + f.getPixelaufloesung());
            lblNennleistung.setText("Nennleistung: " + f.getNennleistung());

        }
    }


    public void ButtonActions() {
        btnAddFernseher.addActionListener(e -> fernseherView.showAddDialog());
    }

}
