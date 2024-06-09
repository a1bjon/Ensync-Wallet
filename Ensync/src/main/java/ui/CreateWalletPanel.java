package ui;

import exceptions.BadNetworkException;

import utils.Constants;
import utils.WalletManager;
import utils.Hasher;

import javax.swing.*;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;

public class CreateWalletPanel extends JPanel{

    // COMPONENT CONSTRAINTS INSTANCE
    private final GridBagConstraints gbc = new GridBagConstraints();

    // WALLET MANAGER
    private final WalletManager wm;

    // LOGIN FRAME INSTANCE
    private final LoginFrame lf;

    // LOGO IN FORM
    private final JLabel logo;

    // WALLET LABEL
    private final JLabel walletLabel;

    // WALLET FIELD
    private final JTextField walletField;

    // PASSWORD LABEL
    private final JLabel passLabel;

    // PASSWORD FIELD
    private final JPasswordField passField;

    // SHOW PASSWORD TEXT BUTTON
    private final JButton showPassButton;

    // GENERATE WALLET BUTTON
    private final JButton generateButton;

    // BACK BUTTON
    private final JButton backButton;

    // WALLET NAME TEXT
    private String walletName;

    // WALLET PASSWORD TEXT
    private String walletPass;

    /**
     * Constructs a CreateWalletPanel object.
     * @param lf Login frame instance.
     * @param wm Wallet manager instance.
     */
    public CreateWalletPanel(LoginFrame lf, WalletManager wm){
        this.setBackground(Constants.GREY12);
        this.setLayout(new GridBagLayout());

        // LOGIN INSTANCE
        this.lf = lf;

        // WALLET MANAGER INSTANCE
        this.wm = wm;

        this.logo = new JLabel(Constants.ENSYNC_LOGO);
        this.gbc.gridx = 0; // Cell 0
        this.gbc.gridy = 0; // Cell 0
        this.gbc.ipadx = 0; // Additional Length
        this.gbc.ipady = 0; // Additional Height
        this.gbc.insets = new Insets(0, 0, 30, 0);
        this.add(this.logo, this.gbc);

        this.walletLabel = new JLabel("Create Wallet Name");
        this.gbc.gridx = 0; // Cell 0
        this.gbc.gridy = 1; // Cell 1
        this.gbc.ipadx = 0; // Additional Length
        this.gbc.ipady = 0; // Additional Height
        this.gbc.insets = new Insets(10, 0, 10, 0);
        this.add(this.walletLabel, this.gbc);

        this.walletField = new JTextField(1);
        this.gbc.gridx = 0; // Cell 0
        this.gbc.gridy = 2; // Cell 2
        this.gbc.ipadx = 230; // Additional Length
        this.gbc.ipady = 0; // Additional Height
        this.gbc.insets = new Insets(10, 0, 10, 0);
        this.walletField.setBorder(null);
        this.walletField.putClientProperty("caretWidth", 3);
        this.requestFieldFocus(this.walletField);
        this.walletField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                fieldOnFocus(true, walletLabel, walletField);
            }

            @Override
            public void focusLost(FocusEvent e) {
                fieldOnFocus(false, walletLabel, walletField);
            }
        });
        this.add(this.walletField, this.gbc);

        this.passLabel = new JLabel("Create Password");
        this.gbc.gridx = 0; // Cell 0
        this.gbc.gridy = 3; // Cell 3
        this.gbc.ipadx = 0; // Additional Length
        this.gbc.ipady = 0; // Additional Height
        this.gbc.insets = new Insets(10, 0, 10, 0);
        this.add(this.passLabel, this.gbc);

        this.passField = new JPasswordField(1);
        this.gbc.gridx = 0; // Cell 0
        this.gbc.gridy = 4; // Cell 4
        this.gbc.ipadx = 230; // Additional Length
        this.gbc.ipady = 0; // Additional Height
        this.gbc.insets = new Insets(10, 0, 10, 0);
        this.passField.setBorder(null);
        this.passField.putClientProperty("caretWidth", 3);
        this.passField.setEchoChar('\u25CF');
        this.passField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                fieldOnFocus(true, passLabel, passField);
            }

            @Override
            public void focusLost(FocusEvent e) {
                fieldOnFocus(false, passLabel, passField);
            }
        });
        this.add(this.passField, this.gbc);

        this.showPassButton = new JButton(Constants.SHOW_PASS_BUTTON_ICON);
        this.gbc.gridx = 0; // Cell 0
        this.gbc.gridy = 4; // Cell 4
        this.gbc.ipadx = 0; // Additional Length
        this.gbc.ipady = 0; // Additional Height
        this.gbc.insets = new Insets(0, 300, 0, 0);
        this.showPassButton.setFocusable(false);
        this.showPassButton.setBorder(null);
        this.showPassButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.showPassButton.setContentAreaFilled(false);
        this.showPassButton.setToolTipText("Show Password");
        this.showPassButton.setPressedIcon(Constants.SHOW_PASS_BUTTON_ICON_ACTIVE);
        this.showPassButton.addActionListener((e) -> this.showPass());
        this.add(this.showPassButton, this.gbc);

        this.generateButton = new JButton(Constants.GENERATE_BUTTON_ICON);
        this.gbc.gridx = 0; // Cell 0
        this.gbc.gridy = 5; // Cell 5
        this.gbc.ipadx = 0; // Additional Length
        this.gbc.ipady = 0; // Additional Height
        this.gbc.insets = new Insets(30, 60, 0, 0);
        this.generateButton.setFocusable(false);
        this.generateButton.setBorder(null);
        this.generateButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.generateButton.setContentAreaFilled(false);
        this.generateButton.setPressedIcon(Constants.GENERATE_BUTTON_ICON_ACTIVE);
        this.generateButton.setToolTipText("Generate a new wallet");
        this.generateButton.addActionListener((e) -> this.create());
        this.add(this.generateButton, this.gbc);

        this.backButton = new JButton(Constants.BACK_BUTTON_ICON);
        this.gbc.gridx = 0; // Cell 0
        this.gbc.gridy = 5; // Cell 6
        this.gbc.ipadx = 0; // Additional Length
        this.gbc.ipady = 0; // Additional Height
        this.gbc.insets = new Insets(30, 0, 0, 220);
        this.backButton.setFocusable(false);
        this.backButton.setBorder(null);
        this.backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.backButton.setContentAreaFilled(false);
        this.backButton.setPressedIcon(Constants.BACK_BUTTON_ICON_ACTIVE);
        this.backButton.setToolTipText("Back to login");
        this.backButton.addActionListener((e) -> this.showLoginPanel());
        this.add(this.backButton, this.gbc);
    }

    /**
     * Changes the colour of the appropriate JLabel
     * to ENSYNC BLUE depending on which field is focused.
     * @param isFocused If a component is focused.
     * @param label Label to change colour when focused.
     * @param field Field to change colour when focused.
     */
    private <T extends JComponent> void fieldOnFocus(boolean isFocused, T label, T field){
        if(isFocused){
            label.setForeground(Constants.ENSYNC_BLUE);
            field.setBackground(Constants.GREY);
        }
        else{
            label.setForeground(Constants.DARK_GREY);
            field.setBackground(Constants.DARK_GREY);
        }
    }

    /**
     * Removes the echo character of the password
     * field if the echo character is equal to '●'
     * otherwise, the echo character is set to '●'
     * and also updates the showPassButton to the active icon.
     */
    private void showPass(){
        if(this.passField.getEchoChar() == '\u25CF'){
            this.passField.setEchoChar((char) 0);
            this.showPassButton.setIcon(Constants.SHOW_PASS_BUTTON_ICON_ACTIVE);
            this.showPassButton.setToolTipText("Hide Password");
        }
        else{
            this.passField.setEchoChar('\u25CF');
            this.showPassButton.setIcon(Constants.SHOW_PASS_BUTTON_ICON);
            this.showPassButton.setToolTipText("Show Password");
        }
    }

    /**
     * Shows the login panel.
     */
    private void showLoginPanel(){
        this.setVisible(false);
        this.lf.loginPanel.setVisible(true);
        // Adding removed logo back fixed logo resizing bug
        this.lf.add(this.lf.logo, BorderLayout.CENTER);
        this.lf.requestFieldFocus(this.lf.walletField);
    }

    /**
     * Generates new wallet using wallet name
     * and password passed.
     * @param walletName Wallet name.
     * @param walletPass Wallet password.
     * @return true if wallet was successfully generated and false otherwise.
     */
    private boolean generateWallet(String walletName, String walletPass){
        boolean successful = false;
        String salt = Hasher.generateSalt();
        try{
            String msg = "Downloading and syncing blockchain.\nThis may take a while.\nSelect OK to start wallet generation.";
            JOptionPane.showMessageDialog(null, msg, "Generating Wallet", JOptionPane.PLAIN_MESSAGE);
            if(this.wm.createWallet(walletName, Hasher.hashString(walletPass + salt), salt)){
                successful = true;
            }
        }
        catch(IOException | BadNetworkException | InterruptedException e){
            String msg = "Could not generate new wallet.";
            JOptionPane.showMessageDialog(null, msg, "Wallet Generation Failed", JOptionPane.ERROR_MESSAGE);
        }
        return successful;
    }

    /**
     * Requests focus of text field passed.
     * @param field Text field to request focus.
     */
    private void requestFieldFocus(JTextField field){
        SwingUtilities.invokeLater(() -> field.requestFocusInWindow());
    }

    /**
     * Checks if current wallet details already exist
     * and if not, a new wallet will be generated with
     * the provided wallet details. Also ensures details
     * provided are sufficient.
     */
    private void create(){
        this.walletName = this.walletField.getText();
        this.walletPass = new String(this.passField.getPassword());
        String msg;

        if(this.walletName.length() == 0 && this.walletPass.length() == 0){
            msg = "Wallet details must be filled to generate new wallet.";
            JOptionPane.showMessageDialog(this.lf, msg, "Details Missing", JOptionPane.ERROR_MESSAGE);
        }
        else if(this.walletName.length() == 0){
            msg = "Wallet name is missing.";
            JOptionPane.showMessageDialog(this.lf, msg, "Details Missing", JOptionPane.ERROR_MESSAGE);
        }
        else if(this.walletPass.length() == 0){
            msg = "Wallet password is missing.";
            JOptionPane.showMessageDialog(this.lf, msg, "Details Missing", JOptionPane.ERROR_MESSAGE);
        }
        else if(this.walletName.strip().length() == 0){
            msg = "Wallet name must include characters.";
            JOptionPane.showMessageDialog(this.lf, msg, "Invalid Wallet Name", JOptionPane.ERROR_MESSAGE);
        }
        else if(this.walletPass.strip().length() == 0){
            msg = "Wallet password must include characters.";
            JOptionPane.showMessageDialog(this.lf, msg, "Invalid Wallet Password", JOptionPane.ERROR_MESSAGE);
        }
        else if(this.walletName.length() > 20){
            msg = "Wallet name wallet must be 20 characters or less.";
            JOptionPane.showMessageDialog(this.lf, msg, "Invalid Wallet Name", JOptionPane.ERROR_MESSAGE);
        }
        else if(this.walletPass.length() < 10){
            msg = "Wallet password must be greater than 10 characters.";
            JOptionPane.showMessageDialog(this.lf, msg, "Invalid Password Length", JOptionPane.ERROR_MESSAGE);
        }
        else if(this.generateWallet(walletName, walletPass)){
            msg = "New wallet" + " [" + this.walletName.strip() + "] " + "generated.";
            JOptionPane.showMessageDialog(this.lf, msg, "Wallet Generated", JOptionPane.INFORMATION_MESSAGE);
            this.showLoginPanel();
        }
    }
}
