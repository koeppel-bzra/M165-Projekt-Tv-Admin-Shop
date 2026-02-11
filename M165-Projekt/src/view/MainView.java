package view;

import controller.FernseherController;
import controller.KundenController;
import controller.ProdukteController;
import model.Fernseher;
import model.Kunde;
import model.Produkt;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    // Controller
    FernseherController fernseherController = new FernseherController();
    KundenController kundenController = new KundenController();
    ProdukteController produkteController = new ProdukteController();

    FernseherView fernseherView = new FernseherView(this, fernseherController);

    JTabbedPane tabbedPane = new JTabbedPane();

    // Fernseher Elemente
    JPanel fernseherPanel = new JPanel(new BorderLayout());
    JPanel fernseherNavPanel = new JPanel(new GridLayout(0, 1, 5, 5));
    JPanel fernseherDetailPanel = new JPanel(new GridLayout(0, 1, 5, 5));
    DefaultListModel<String> fernseherListModel = new DefaultListModel<>();
    JList<String> fernseherList = new JList<>(fernseherListModel);

    JButton btnAddFernseher = new JButton("Hinzufügen");
    JButton btnUpdateFernseher = new JButton("Aktualisieren");
    JButton btnDeleteFernseher = new JButton("Löschen");

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


    // Kunden Elemente
    JPanel kundenPanel = new JPanel(new BorderLayout());
    JPanel kundenNavPanel = new JPanel(new GridLayout(0,1,5,5));
    JPanel kundenDetailPanel = new JPanel(new GridLayout(0, 1, 5 ,5));
    DefaultListModel<String> kundenListModel = new DefaultListModel<>();
    JList<String> kundenList = new JList<>(kundenListModel);

    JButton btnAddKunden = new JButton("Hinzufügen");
    JButton btnUpdateKunden = new JButton("Aktualisieren");
    JButton btnDeleteKunden = new JButton("Löschen");

    JLabel lblAnrede = new JLabel("Anrede: ");
    JLabel lblNachname = new JLabel("Nachname: ");
    JLabel lblVorname = new JLabel("Vorname: ");
    JLabel lblTelefonPrivat = new JLabel("Telefon Privat: ");
    JLabel lblTelefonMobile = new JLabel("Telefon Mobile: ");
    JLabel lblEmail = new JLabel("Email: ");
    JLabel lblGeburtsdatum = new JLabel("Geburtsdatum: ");
    JLabel lblUsername = new JLabel("Benutzername: ");


    // Produkte Elemente
    JPanel produktePanel = new JPanel(new BorderLayout());
    JPanel produkteNavPanel = new JPanel(new GridLayout(0,1,5,5));
    DefaultListModel<String> produkteListModel = new DefaultListModel<>();
    JList<String> produkteList = new JList<>(produkteListModel);

    JButton btnAddProdukte = new JButton("Hinzufügen");
    JButton btnUpdateProdukte = new JButton("Aktualisieren");
    JButton btnDeleteProdukte = new JButton("Löschen");

    public MainView() {
        setSize(1000, 600);
        setTitle("Tv-Shop");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Fernseher aufbauen
        fernseherNavPanel.add(btnAddFernseher);
        fernseherNavPanel.add(btnUpdateFernseher);
        fernseherNavPanel.add(btnDeleteFernseher);
        fernseherNavPanel.setPreferredSize(new Dimension(200, 0));

        JScrollPane fernseherScroll = new JScrollPane(fernseherList);
        fernseherPanel.add(fernseherNavPanel, BorderLayout.WEST);
        fernseherPanel.add(fernseherScroll, BorderLayout.CENTER);

        fernseherDetailPanel.add(lblMarke);
        fernseherDetailPanel.add(lblModell);
        fernseherDetailPanel.add(lblPreis);
        fernseherDetailPanel.add(lblBildschirmdiagonale);
        fernseherDetailPanel.add(lblDisplaytechnologie);
        fernseherDetailPanel.add(lblBildschirmaufloesung);
        fernseherDetailPanel.add(lblBildwiederholfrequenz);
        fernseherDetailPanel.add(lblGewicht);
        fernseherDetailPanel.add(lblReleasedatum);
        fernseherDetailPanel.add(lblPixelaufloesung);
        fernseherDetailPanel.add(lblNennleistung);
        fernseherDetailPanel.setPreferredSize(new Dimension(300, 0));

        fernseherPanel.add(fernseherDetailPanel, BorderLayout.EAST);

        // Listener für Liste
        fernseherList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) updateFernseherDetails();
        });


        // Kunden Aufbau
        kundenNavPanel.add(btnAddKunden);
        kundenNavPanel.add(btnUpdateKunden);
        kundenNavPanel.add(btnDeleteKunden);
        kundenNavPanel.setPreferredSize(new Dimension(200, 0));

        JScrollPane kundenScroll = new JScrollPane(kundenList);
        kundenPanel.add(kundenNavPanel, BorderLayout.WEST);
        kundenPanel.add(kundenScroll, BorderLayout.CENTER);

        kundenDetailPanel.add(lblAnrede);
        kundenDetailPanel.add(lblNachname);
        kundenDetailPanel.add(lblVorname);
        kundenDetailPanel.add(lblTelefonPrivat);
        kundenDetailPanel.add(lblTelefonMobile);
        kundenDetailPanel.add(lblEmail);
        kundenDetailPanel.add(lblGeburtsdatum);
        kundenDetailPanel.add(lblUsername);
        kundenDetailPanel.setPreferredSize(new Dimension(300, 0));

        kundenPanel.add(kundenDetailPanel, BorderLayout.EAST);


        // Produkte Aufbau
        produkteNavPanel.add(btnAddProdukte);
        produkteNavPanel.add(btnUpdateProdukte);
        produkteNavPanel.add(btnDeleteProdukte);
        produkteNavPanel.setPreferredSize(new Dimension(200, 0));

        JScrollPane produkteScroll = new JScrollPane(produkteList);
        produktePanel.add(produkteNavPanel, BorderLayout.WEST);
        produktePanel.add(produkteScroll, BorderLayout.CENTER);


        // Tabs hinzufügen
        tabbedPane.addTab("Fernseher", fernseherPanel);
        tabbedPane.addTab("Kunden", kundenPanel);
        tabbedPane.addTab("Produkte", produktePanel);
        add(tabbedPane);


        // ActionListener für Fernseher
        btnAddFernseher.addActionListener(e -> {
            fernseherView.showAddDialog();
        });

        btnUpdateFernseher.addActionListener(e -> {
            fernseherView.showUpdateDialog();
        });

        btnDeleteFernseher.addActionListener(e -> {
            fernseherView.deleteFernseherUI();
        });


        refreshFernseherList();
        setVisible(true);
    }


    // Fernseher Methoden

    public void refreshFernseherList() {
        fernseherListModel.clear();
        for (Fernseher f : fernseherController.getAllFernseher()) {
            fernseherListModel.addElement(f.getMarke() + " - " + f.getModell());
        }
    }

    public void updateFernseherDetails() {
        int index = fernseherList.getSelectedIndex();
        if (index >= 0 && index < fernseherController.getAllFernseher().size()) {
            Fernseher f = fernseherController.getAllFernseher().get(index);
            lblMarke.setText("Marke: " + f.getMarke());
            lblModell.setText("Modell: " + f.getModell());
            lblPreis.setText("Preis: " + f.getPreis() + " CHF");
            lblBildschirmdiagonale.setText("Bildschirmdiagonale: " + f.getBildschirmdiagonale() + "'");
            lblDisplaytechnologie.setText("Displaytechnologie: " + f.getDisplayTechnologie());
            lblBildschirmaufloesung.setText("Bildschirmauflösung: " + f.getBildschirmAufloesung());
            lblBildwiederholfrequenz.setText("Bildwiederholfrequenz: " + f.getBildwiederholFrequenz() + "Hz");
            lblGewicht.setText("Gewicht: " + f.getGewicht() + "kg");
            lblReleasedatum.setText("Releasedatum: " + f.getReleaseDatum());
            lblPixelaufloesung.setText("Pixelauflösung: " + f.getPixelaufloesung());
            lblNennleistung.setText("Nennleistung: " + f.getNennleistung());
        }
    }

}
