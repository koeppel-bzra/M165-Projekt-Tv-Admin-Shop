package view;

import controller.FernseherController;
import controller.KundenController;
import controller.BestellungenController;
import model.Bestellposition;
import model.Bestellung;
import model.Fernseher;
import model.Kunde;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainView extends JFrame {

    // Controller
    FernseherController fernseherController = new FernseherController();
    KundenController kundenController = new KundenController();
    BestellungenController bestellungenController = new BestellungenController();

    FernseherView fernseherView = new FernseherView(this, fernseherController);
    KundenView kundenView = new KundenView(this, kundenController);
    BestellungView bestellungView = new BestellungView(this, bestellungenController, fernseherController, kundenController);

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
    JLabel lblPassword = new JLabel("Passwort: ");



    // Bestellungen Elemente
    JPanel bestellungenPanel = new JPanel(new BorderLayout());
    JPanel bestellungenNavPanel = new JPanel(new GridLayout(0,1,5,5));
    JPanel bestellungenDetailPanel = new JPanel(new GridLayout(0, 1, 5 ,5));
    DefaultListModel<String> bestellungenListModel = new DefaultListModel<>();
    JList<String> bestellungenList = new JList<>(bestellungenListModel);

    JButton btnAddBestellungen = new JButton("Hinzufügen");
    JButton btnUpdateBestellungen = new JButton("Aktualisieren");
    JButton btnDeleteBestellungen = new JButton("Löschen");

    JLabel lblBestellnummer = new JLabel("Bestellnummer: ");
    JLabel lblBestelldatum = new JLabel("Bestelldatum: ");
    JLabel lblKunde = new JLabel("Kunde: ");
    JLabel lblFernseher = new JLabel("Fernseher: ");
    JLabel lblEinzelpreis = new JLabel("Einzelpreis: ");
    JLabel lblMenge = new JLabel("Menge: ");

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
            if (!e.getValueIsAdjusting()) {
                updateFernseherDetails();
            }
        });

        kundenList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                updateKundenDetails();
            }
        });

        bestellungenList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                updateBestellungenDetails();
            }
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
        kundenDetailPanel.add(lblPassword);
        kundenDetailPanel.setPreferredSize(new Dimension(300, 0));

        kundenPanel.add(kundenDetailPanel, BorderLayout.EAST);


        // Bestellungen Aufbau
        bestellungenNavPanel.add(btnAddBestellungen);
        bestellungenNavPanel.add(btnUpdateBestellungen);
        bestellungenNavPanel.add(btnDeleteBestellungen);
        bestellungenNavPanel.setPreferredSize(new Dimension(200, 0));

        JScrollPane bestellungenScroll = new JScrollPane(bestellungenList);
        bestellungenPanel.add(bestellungenNavPanel, BorderLayout.WEST);
        bestellungenPanel.add(bestellungenScroll, BorderLayout.CENTER);

        bestellungenDetailPanel.add(lblBestellnummer);
        bestellungenDetailPanel.add(lblBestelldatum);
        bestellungenDetailPanel.add(lblKunde);
        bestellungenDetailPanel.add(lblFernseher);
        bestellungenDetailPanel.add(lblEinzelpreis);
        bestellungenDetailPanel.add(lblMenge);
        bestellungenDetailPanel.setPreferredSize(new Dimension(300, 0));

        bestellungenPanel.add(bestellungenDetailPanel, BorderLayout.EAST);


        // Tabs hinzufügen
        tabbedPane.addTab("Fernseher", fernseherPanel);
        tabbedPane.addTab("Kunden", kundenPanel);
        tabbedPane.addTab("Bestellungen", bestellungenPanel);
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


        // ActionListener für Kunden
        btnAddKunden.addActionListener(e -> {
            kundenView.showAddDialog();
        });

        btnDeleteKunden.addActionListener(e -> {
            kundenView.deleteKundeUI();
        });

        btnUpdateKunden.addActionListener(e -> {
            kundenView.showUpdateDialog();
        });


        // ActionLIstener für Bestellungen
        btnAddBestellungen.addActionListener(e -> {
            bestellungView.showAddDialog();
        });

        btnUpdateBestellungen.addActionListener(e -> {
            bestellungView.showUpdateDialog();
        });

        btnDeleteBestellungen.addActionListener(e -> {
            bestellungView.deleteBestellung();
        });


        refreshFernseherList();
        refreshKundenList();
        refreshBestellungenList();
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


    // Kunden Methoden

    public void refreshKundenList() {
        kundenListModel.clear();
        for (Kunde k : kundenController.getAllKunden()) {
            kundenListModel.addElement(k.getNachname() + " " + k.getVorname());
        }
    }

    public void updateKundenDetails() {
        int index = kundenList.getSelectedIndex();

        if (index == -1) {
            lblAnrede.setText("Anrede: ");
            lblNachname.setText("Nachname: ");
            lblVorname.setText("Vorname: ");
            lblTelefonPrivat.setText("Telefon Privat: ");
            lblTelefonMobile.setText("Telefon Mobile: ");
            lblEmail.setText("Email: ");
            lblGeburtsdatum.setText("Geburtsdatum: ");
            lblUsername.setText("Benutzername: ");
            lblPassword.setText("Passwort: ");
            return;
        }

        if (index >= 0 && index < kundenController.getAllKunden().size()) {
            Kunde k = kundenController.getAllKunden().get(index);
            lblAnrede.setText("Anrede: " + k.getAnrede());
            lblNachname.setText("Nachname: " + k.getNachname());
            lblVorname.setText("Vorname: " + k.getVorname());
            lblTelefonPrivat.setText("Telefon Privat: " + k.getTelefonPrivat());
            lblTelefonMobile.setText("Telefon Mobile: " + k.getTelefonMobile());
            lblEmail.setText("Email: " + k.getEmail());
            lblGeburtsdatum.setText("Geburtsdatum: " + k.getGeburtsdatum());
            lblUsername.setText("Benutzername: " + k.getUsername());
            lblPassword.setText("Passwort: " + kundenView.hashPassword(k.getPassword()));
        }
    }


    // Bestellungen Methoden

    public void refreshBestellungenList() {
        bestellungenListModel.clear();

        for (Bestellung bestellung : bestellungenController.getAllBestellungen()) {
            bestellungenListModel.addElement(bestellung.getBestellnummer() + " - " + bestellung.getKunde().getNachname() + " " + bestellung.getKunde().getVorname());
        }
    }

    public void updateBestellungenDetails() {
        int index = bestellungenList.getSelectedIndex();

        if (index == -1) {
            lblBestellnummer.setText("Bestellnummer: ");
            lblBestelldatum.setText("Bestelldatum: ");
            lblKunde.setText("Kunde: ");
            lblFernseher.setText("Fernseher: ");
            lblMenge.setText("Menge: ");
            return;
        }

        Bestellung bestellung = bestellungenController.getAllBestellungen().get(index);
        List<Bestellposition> bestellpositionen = bestellung.getBestellpositionen();

        lblBestellnummer.setText("Bestellnummer: " + bestellung.getBestellnummer());
        lblBestelldatum.setText("Bestelldatum: " + bestellung.getBestellDatum());
        lblKunde.setText("Kunde: " + bestellung.getKunde());

        for (Bestellposition pos : bestellpositionen) {
            lblFernseher.setText("Fernseher: " + pos.getFernseher());
            lblEinzelpreis.setText("Einzelpreis: " + pos.getEinzelpreis());
            lblMenge.setText("Menge: " + pos.getStueckzahl());
        }

    }


}
