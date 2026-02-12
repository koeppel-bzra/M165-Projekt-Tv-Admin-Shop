package view;

import controller.KundenController;
import model.Kunde;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Properties;
import java.awt.event.ActionListener;

public class KundenView {

    private KundenController controller;
    private MainView view;

    // Komponenten
    private JLabel lblAnrede = new JLabel("Anrede");
    private JTextField txtAnrede = new JTextField();

    private JLabel lblNachname = new JLabel("Nachname");
    private JTextField txtNachname = new JTextField();

    private JLabel lblVorname = new JLabel("Vorname");
    private JTextField txtVorname = new JTextField();

    private JLabel lblTelefonPrivate = new JLabel("Telefon Privat");
    private JTextField txtTelefonPrivate = new JTextField();

    private JLabel lblTelefonMobile = new JLabel("Telefon Mobile");
    private JTextField txtTelefonMobile = new JTextField();

    private JLabel lblEmail = new JLabel("Email");
    private JTextField txtEmail = new JTextField();

    private JLabel lblGeburtsdatum = new JLabel("Geburtsdatum");

    private UtilDateModel dateModel = new UtilDateModel();
    private JDatePickerImpl datePicker;

    private JLabel lblUsername = new JLabel("Benutzername");
    private JTextField txtUsername = new JTextField();

    private JLabel lblPassword = new JLabel("Passwort");
    private JPasswordField txtPassword = new JPasswordField();

    private JLabel lblWhiteSpace = new JLabel();
    private JButton btnAction = new JButton("Hinzufügen");

    private JDialog dialog = new JDialog();

    public KundenView(MainView view, KundenController controller) {
        this.view = view;
        this.controller = controller;

        dateModel.setSelected(true);

        Properties p = new Properties();
        p.put("text.today", "Heute");
        p.put("text.month", "Monat");
        p.put("text.year", "Jahr");

        JDatePanelImpl datePanel = new JDatePanelImpl(dateModel, p);
        datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
    }


    public void showAddDialog() {
        clearFields();
        btnAction.setText("Hinzufügen");

        for (ActionListener al : btnAction.getActionListeners()) {
            btnAction.removeActionListener(al);
        }

        btnAction.addActionListener(e -> addKunde());

        setupDialog();
    }

    private void addKunde() {
        try {
            Kunde k = buildKundeFromFields();
            controller.addKunde(k);
            view.refreshKundenList();
            dialog.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(dialog, "Ungültige Eingabe");
        }
    }


    public void showUpdateDialog() {
        String selectedString = view.kundenList.getSelectedValue();

        if (selectedString == null) {
            JOptionPane.showMessageDialog(view, "Kein Kunde ausgewählt");
            return;
        }


        Kunde kundeToUpdate = controller.getAllKunden().stream()
                .filter(k -> (k.getAnrede() + " - " + k.getNachname() + " - " + k.getVorname()).equals(selectedString))
                .findFirst()
                .orElse(null);

        if (kundeToUpdate == null) {
            JOptionPane.showMessageDialog(view, "Kunde nicht gefunden");
            return;
        }

        fillFields(kundeToUpdate);

        btnAction.setText("Aktualisieren");

        for (ActionListener al : btnAction.getActionListeners()) {
            btnAction.removeActionListener(al);
        }

        btnAction.addActionListener(e -> updateKundeUI(kundeToUpdate));

        setupDialog();
    }


    private void updateKundeUI(Kunde k) {
        try {
            k.setAnrede(txtAnrede.getText());
            k.setNachname(txtNachname.getText());
            k.setVorname(txtVorname.getText());
            k.setTelefonPrivat(txtTelefonPrivate.getText());
            k.setTelefonMobile(txtTelefonMobile.getText());
            k.setEmail(txtEmail.getText());
            k.setGeburtsdatum(
                    LocalDate.ofInstant(
                            ((Date) datePicker.getModel().getValue()).toInstant(),
                            ZoneId.systemDefault()
                    )
            );
            k.setUsername(txtUsername.getText());
            k.setPassword(txtPassword.getText());

            controller.updateKunde(k);

            view.refreshKundenList();
            view.updateKundenDetails();

            dialog.dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(dialog, "Ungültige Eingabe");
        }
    }



    public void deleteKundeUI() {
        String selectedString = view.kundenList.getSelectedValue();

        if (selectedString == null) {
            JOptionPane.showMessageDialog(view, "Kein Kunde ausgewählt");
            return;
        }

        Kunde selected = null;

        for (Kunde k : controller.getAllKunden()) {
            if ((k.getAnrede() + " - " + k.getNachname() + " - " + k.getVorname()).equals(selectedString)) {
                selected = k;
                break;
            }
        }

        if (selected != null) {

            int result = JOptionPane.showConfirmDialog(
                    view,
                    "Möchtest du den Kunden wirklich löschen?",
                    "Löschen bestätigen",
                    JOptionPane.YES_NO_OPTION
            );

            if (result == JOptionPane.YES_OPTION) {
                controller.deleteKunde(selected);
                view.refreshKundenList();
                view.updateKundenDetails();
            }
        }
    }


    private void setupDialog() {

        dialog.setSize(400, 400);
        dialog.setLocationRelativeTo(view);
        dialog.setLayout(new GridLayout(0, 2, 5, 5));

        dialog.add(lblAnrede);
        dialog.add(txtAnrede);
        dialog.add(lblNachname);
        dialog.add(txtNachname);
        dialog.add(lblVorname);
        dialog.add(txtVorname);
        dialog.add(lblTelefonPrivate);
        dialog.add(txtTelefonPrivate);
        dialog.add(lblTelefonMobile);
        dialog.add(txtTelefonMobile);
        dialog.add(lblEmail);
        dialog.add(txtEmail);
        dialog.add(lblGeburtsdatum);
        dialog.add(datePicker);
        dialog.add(lblUsername);
        dialog.add(txtUsername);
        dialog.add(lblPassword);
        dialog.add(txtPassword);
        dialog.add(lblWhiteSpace);
        dialog.add(btnAction);

        dialog.setVisible(true);
    }


    private void clearFields() {
        txtAnrede.setText("");
        txtNachname.setText("");
        txtVorname.setText("");
        txtTelefonPrivate.setText("");
        txtTelefonMobile.setText("");
        txtEmail.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
        dateModel.setValue(null);
    }

    private void fillFields(Kunde k) {
        txtAnrede.setText(k.getAnrede());
        txtNachname.setText(k.getNachname());
        txtVorname.setText(k.getVorname());
        txtTelefonPrivate.setText(k.getTelefonPrivat());
        txtTelefonMobile.setText(k.getTelefonMobile());
        txtEmail.setText(k.getEmail());
        txtUsername.setText(k.getUsername());
        txtPassword.setText(k.getPassword());

        dateModel.setValue(
                Date.from(
                        k.getGeburtsdatum()
                                .atStartOfDay(ZoneId.systemDefault())
                                .toInstant()
                )
        );
    }

    private Kunde buildKundeFromFields() {
        return new Kunde(
                txtAnrede.getText(),
                txtNachname.getText(),
                txtVorname.getText(),
                txtTelefonPrivate.getText(),
                txtTelefonMobile.getText(),
                txtEmail.getText(),
                LocalDate.ofInstant(
                        ((Date) datePicker.getModel().getValue()).toInstant(),
                        ZoneId.systemDefault()
                ),
                txtUsername.getText(),
                hashPassword(txtPassword.getText())
        );
    }




    public String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
