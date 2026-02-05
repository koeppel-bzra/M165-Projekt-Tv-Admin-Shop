package view;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    FernseherView fernseherView = new FernseherView();

    JPanel panel = new JPanel(new BorderLayout());
    JPanel navPanel = new JPanel(new GridLayout(0, 1, 5, 5));
    JPanel midPanel = new JPanel();

    JButton btnAddFernseher = new JButton("Fernseher hinzufügen");
    JButton btnShowAllFernseher = new JButton("Alle Fernseher anzeigen");

    public MainView() {
        setSize(1000, 600);
        setTitle("TV-Verwaltung");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(panel);
        panel.add(navPanel, BorderLayout.WEST);
        panel.add(midPanel, BorderLayout.CENTER);

        Navigation();
        ButtonActions();

        setVisible(true);
    }

    public void Navigation() {
        navPanel.add(btnAddFernseher);
        navPanel.add(btnShowAllFernseher);
    }

    public void ButtonActions() {
        btnAddFernseher.addActionListener(e -> fernseherView.showAddDialog());
    }
}
