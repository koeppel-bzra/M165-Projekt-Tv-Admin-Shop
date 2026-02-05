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

    // Detail Labels
    JLabel lblMarke = new JLabel("Marke: ");
    JLabel lblModell = new JLabel("Modell: ");

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
        detailPanel.setLayout(new GridLayout(2,1,5,5));
        detailPanel.add(lblMarke);
        detailPanel.add(lblModell);
        detailPanel.setPreferredSize(new Dimension(200, 0));
    }

    public void DetailList() {
        int index = list.getSelectedIndex();
        if (index >= 0) {
            String item = listModel.get(index);
            String[] parts = item.split(" - ");
            lblMarke.setText("Marke: " + parts[0]);
            lblModell.setText("Modell: " + parts[1]);
        }
    }


    public void ButtonActions() {
        btnAddFernseher.addActionListener(e -> fernseherView.showAddDialog());
    }

}
