package ui;

import exceptions.BadNetworkException;

import utils.Constants;
import utils.UITheme;
import utils.WalletManager;
import utils.Audio;

import javax.swing.*;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;

public class LoginFrame extends JFrame{

    // COMPONENT CONSTRAINTS INSTANCE
    private final GridBagConstraints gbc = new GridBagConstraints();

    // WALLET MANAGER
    private final WalletManager wm;

    // LOGO LEFT OF FORM
    final JLabel logo;

    // LOGIN FORM PANEL
    final JPanel loginPanel;

    // LOGO IN FORM
    private final JLabel loginLogoLabel;

    // WALLET LABEL
    private final JLabel walletLabel;

    // WALLET FIELD
    final JTextField walletField;

    // PASSWORD LABEL
    private final JLabel passLabel;

    // PASSWORD FIELD
    private final JPasswordField passField;

    // SHOW PASSWORD TEXT BUTTON
    private final JButton showPassButton;

    // LOGIN BUTTON
    private final JButton loginButton;

    // REGISTER BUTTON
    private final JButton registerButton;

    // WALLET NAME TEXT
    private String walletName;

    // WALLET PASSWORD TEXT
    private String walletPass;

    /**
     * Constructs a LoginFrame object.
     */
    public LoginFrame(){
        super(Constants.WINDOW_TITLE);
        UITheme.setTheme();
        this.setIconImage(Constants.WINDOW_ICON);
        this.setSize(Constants.LOGIN_WINDOW_DIMENSION);
        this.setMinimumSize(Constants.MINIMUM_LOGIN_WINDOW_SIZE);
        this.setContentPane(new JLabel(Constants.LOGIN_BACKGROUND));
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        this.loginPanel = new JPanel(new GridBagLayout());

        this.loginLogoLabel = new JLabel(Constants.ENSYNC_LOGO);
        this.gbc.gridx = 0; // Cell 0
        this.gbc.gridy = 0; // Cell 0
        this.gbc.ipadx = 0; // Additional Length
        this.gbc.ipady = 0; // Additional Height
        this.gbc.insets = new Insets(0, 20, 30, 0);
        this.loginPanel.add(this.loginLogoLabel, this.gbc);

        this.walletLabel = new JLabel("Wallet");
        this.gbc.gridx = 0; // Cell 0
        this.gbc.gridy = 1; // Cell 1
        this.gbc.ipadx = 0; // Additional Length
        this.gbc.ipady = 0; // Additional Height
        this.gbc.insets = new Insets(0, 20, 10, 0);
        this.loginPanel.add(this.walletLabel, this.gbc);

        this.walletField = new JTextField(1);
        this.gbc.gridx = 0; // Cell 0
        this.gbc.gridy = 2; // Cell 2
        this.gbc.ipadx = 230; // Additional Length
        this.gbc.ipady = 0; // Additional Height
        this.gbc.insets = new Insets(10, 180, 10, 160);
        this.walletField.setBorder(null);
        this.walletField.putClientProperty("caretWidth", 3);
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
        this.loginPanel.add(this.walletField, this.gbc);

        this.passLabel = new JLabel("Password");
        this.gbc.gridx = 0; // Cell 0
        this.gbc.gridy = 3; // Cell 3
        this.gbc.ipadx = 0; // Additional Length
        this.gbc.ipady = 0; // Additional Height
        this.gbc.insets = new Insets(10, 20, 10, 0);
        this.loginPanel.add(this.passLabel, this.gbc);

        this.passField = new JPasswordField(1);
        this.gbc.gridx = 0; // Cell 0
        this.gbc.gridy = 4; // Cell 4
        this.gbc.ipadx = 230; // Additional Length
        this.gbc.ipady = 0; // Additional Height
        this.gbc.insets = new Insets(10, 180, 10, 160);
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
        this.loginPanel.add(this.passField, this.gbc);

        this.showPassButton = new JButton(Constants.SHOW_PASS_BUTTON_ICON);
        this.gbc.gridx = 0; // Cell 0
        this.gbc.gridy = 4; // Cell 4
        this.gbc.ipadx = 0; // Additional Length
        this.gbc.ipady = 0; // Additional Height
        this.gbc.insets = new Insets(0, 320, 0, 0);
        this.showPassButton.setFocusable(false);
        this.showPassButton.setBorder(null);
        this.showPassButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.showPassButton.setContentAreaFilled(false);
        this.showPassButton.setToolTipText("Show Password");
        this.showPassButton.setPressedIcon(Constants.SHOW_PASS_BUTTON_ICON_ACTIVE);
        this.showPassButton.addActionListener((e) -> this.showPass());
        this.loginPanel.add(this.showPassButton, this.gbc);

        this.loginButton = new JButton(Constants.LOGIN_BUTTON_ICON);
        this.gbc.gridx = 0; // Cell 0
        this.gbc.gridy = 5; // Cell 5
        this.gbc.ipadx = 0; // Additional Length
        this.gbc.ipady = 0; // Additional Height
        this.gbc.insets = new Insets(30, 0, 0, 160);
        this.loginButton.setFocusable(false);
        this.loginButton.setBorder(null);
        this.loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.loginButton.setContentAreaFilled(false);
        this.loginButton.setPressedIcon(Constants.LOGIN_BUTTON_ICON_ACTIVE);
        this.activateButton();
        this.loginButton.addActionListener((e) -> this.checkLogin());
        this.loginPanel.add(this.loginButton, this.gbc);

        this.registerButton = new JButton(Constants.REGISTER_BUTTON_ICON);
        this.gbc.gridx = 0; // Cell 0
        this.gbc.gridy = 5; // Cell 5
        this.gbc.ipadx = 0; // Additional Length
        this.gbc.ipady = 0; // Additional Height
        this.gbc.insets = new Insets(30, 300, 0, 160);
        this.registerButton.setFocusable(false);
        this.registerButton.setBorder(null);
        this.registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.registerButton.setContentAreaFilled(false);
        this.registerButton.setPressedIcon(Constants.REGISTER_BUTTON_ICON_ACTIVE);
        this.registerButton.setToolTipText("Create New Wallet");
        this.registerButton.addActionListener((e) -> this.showWalletGenPanel());
        this.loginPanel.add(this.registerButton, this.gbc);

        this.add(this.loginPanel, BorderLayout.EAST);

        this.logo = new JLabel(Constants.ENSYNC_LOGO_WITH_NAME);
        this.add(this.logo, BorderLayout.CENTER);

        this.setVisible(true);

        // Setting wallet manager value and calling
        // after UI is displayed to avoid missing
        // Database error appearing before UI is
        // displayed.
        this.wm = new WalletManager();
    }

    /**
     * Changes the colour of the appropriate JLabel
     * and JField depending on which field is focused.
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
     * Requests focus of text field passed.
     * @param field Text field to request focus.
     */
    void requestFieldFocus(JTextField field){
        SwingUtilities.invokeLater(() -> field.requestFocusInWindow());
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
     * Shows the wallet generation panel.
     */
    private void showWalletGenPanel(){
        this.walletField.setText("");
        this.passField.setText("");
        this.showPassButton.setIcon(Constants.SHOW_PASS_BUTTON_ICON);
        this.passField.setEchoChar('\u25CF');
        // Removing logo fixed logo resizing bug
        this.remove(this.logo);
        this.loginPanel.setVisible(false);
        this.add(new CreateWalletPanel(this, this.wm), BorderLayout.CENTER);
    }

    /**
     * Activates the Login button when the wallet
     * and password field are not empty. Otherwise, the
     * Login button is not enabled.
     */
    private void activateButton(){
        Thread t = new Thread(new Runnable(){
            @Override
            public void run(){
                try{
                    while(true){
                        // Real time field values
                        String walletText = walletField.getText();
                        String walletPass = new String(passField.getPassword());

                        if(walletText.length() != 0 && walletPass.length() != 0){
                            loginButton.setEnabled(true);
                            loginButton.setToolTipText("Login To Wallet");
                            continue;
                        }

                        loginButton.setEnabled(false);
                        loginButton.setToolTipText(null);
                    }
                }
                catch(NullPointerException e){
                    // Null pointer sometimes getting thrown and
                    // breaking loop.
                    this.run();
                }
            }
        });
        t.setPriority(Thread.NORM_PRIORITY);
        t.start();
    }

    /**
     * Checks if wallet details provided are valid
     * and if they are, the user will be logged in
     * to the main application.
     */
    private void checkLogin(){
        String msg;
        this.walletName = this.walletField.getText();
        this.walletPass = new String(this.passField.getPassword());

        try{
            if(this.walletName.strip().length() == 0){
                msg = "Wallet name must include characters.";
                JOptionPane.showMessageDialog(this, msg, "Invalid Wallet Name", JOptionPane.ERROR_MESSAGE);
            }
            else if(this.walletPass.strip().length() == 0){
                msg = "Wallet password must include characters.";
                JOptionPane.showMessageDialog(this, msg, "Invalid Password", JOptionPane.ERROR_MESSAGE);
            }
            else if(this.wm.login(walletName, walletPass)){
                this.wm.loggedInWallet = walletName.strip();
                Audio.playAudio("src/main/resources/audio/login_success.wav");
                this.dispose();
                new AppFrame(this.wm);
            }
            else{
                msg = "Wallet name or password is wrong.\nYou can create a new wallet below.";
                JOptionPane.showMessageDialog(this, msg, "Incorrect Wallet", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(BadNetworkException | IOException e){
            msg = "Could not login to wallet.";
            JOptionPane.showMessageDialog(this, msg, "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
}
