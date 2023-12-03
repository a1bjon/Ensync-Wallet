package ui;

import utils.Constants;
import utils.WalletManager;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

public class SettingsPanel extends JPanel {

    // COMPONENT CONSTRAINTS INSTANCE
    private final GridBagConstraints gbc = new GridBagConstraints();

    // WALLET MANAGER
    private final WalletManager wm;

    // APP FRAME
    private final AppFrame af;

    // DASHBOARD PANEL
    private final DashboardPanel dp;

    // COMPONENT PANEL
    private final JPanel componentPanel;

    // CURRENCY TITLE LABEL
    private final JLabel currencyTitleLabel;

    // POUND BUTTON
    private final JButton poundButton;

    // EURO BUTTON
    private final JButton euroButton;

    // DOLLAR BUTTON
    private final JButton dollarButton;

    // POUND BUTTON
    private final JButton ausDollarButton;

    // EURO BUTTON
    private final JButton liraButton;

    // DOLLAR BUTTON
    private final JButton zlotyButton;

    // UPDATE WALLET NAME TITLE LABEL
    private final JLabel updateWalletNameTitleLabel;

    // UPDATE WALLET NAME FIELD
    private final JTextField updateWalletNameField;

    // UPDATE WALLET NAME BUTTON
    private final JButton updateWalletNameButton;

    // DELETE WALLET TITLE LABEL
    private final JLabel deleteWalletTitleLabel;

    // DELETE WALLET BUTTON
    private final JButton deleteWalletButton;

    // VIEW RECOVERY PHRASE TITLE LABEL
    private final JLabel viewRecoveryTitleLabel;

    // VIEW RECOVERY PHRASE BUTTON
    private final JButton viewRecoveryButton;

    // SCROLL PANE
    private final JScrollPane scrollPane;

    /**
     * Constructs a SettingsPanel object.
     * @param wm Wallet manager instance.
     * @param af App frame instance.
     * @param dp Dashboard panel instance.
     */
    public SettingsPanel(WalletManager wm, AppFrame af, DashboardPanel dp){
        this.setLayout(new GridBagLayout());
        this.setBackground(Constants.GREY12);

        // WALLET MANAGER INSTANCE
        this.wm = wm;

        // APP FRAME INSTANCE
        this.af = af;

        // DASHBOARD PANEL INSTANCE
        this.dp = dp;

        // COMPONENT PANEL COMPONENTS CONSTRAINTS
        GridBagConstraints componentPanelGbc = new GridBagConstraints();
        this.componentPanel = new JPanel();
        this.componentPanel.setLayout(new GridBagLayout());
        this.componentPanel.setBackground(Constants.GREY12);

        this.currencyTitleLabel = new JLabel("Currency");
        componentPanelGbc.gridx = 1; // Cell 1
        componentPanelGbc.gridy = 0; // Cell 0
        componentPanelGbc.ipadx = 0; // Additional Length
        componentPanelGbc.ipady = 0; // Additional Height
        componentPanelGbc.insets = new Insets(0, 0, 10, 0);
        this.currencyTitleLabel.setForeground(Constants.GREY);
        this.currencyTitleLabel.setFont(new Font("consolas", Font.BOLD, 30));
        this.componentPanel.add(this.currencyTitleLabel, componentPanelGbc);

        this.poundButton = new JButton(Constants.POUND_BUTTON_ICON_ACTIVE);
        componentPanelGbc.gridx = 0; // Cell 0
        componentPanelGbc.gridy = 1; // Cell 1
        componentPanelGbc.ipadx = 0; // Additional Length
        componentPanelGbc.ipady = 0; // Additional Height
        componentPanelGbc.insets = new Insets(10, 0, 0, 0);
        this.poundButton.setBorder(null);
        this.poundButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.poundButton.setContentAreaFilled(false);
        this.poundButton.setPressedIcon(Constants.POUND_BUTTON_ICON_ACTIVE);
        this.poundButton.setToolTipText("Great British Pound");
        this.poundButton.addActionListener((e) -> this.changeToPound());
        this.componentPanel.add(this.poundButton, componentPanelGbc);

        this.euroButton = new JButton(Constants.EURO_BUTTON_ICON);
        componentPanelGbc.gridx = 1; // Cell 1
        componentPanelGbc.gridy = 1; // Cell 1
        componentPanelGbc.ipadx = 0; // Additional Length
        componentPanelGbc.ipady = 0; // Additional Height
        componentPanelGbc.insets = new Insets(10, 0, 0, 0);
        this.euroButton.setBorder(null);
        this.euroButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.euroButton.setContentAreaFilled(false);
        this.euroButton.setPressedIcon(Constants.EURO_BUTTON_ICON_ACTIVE);
        this.euroButton.setToolTipText("Euro");
        this.euroButton.addActionListener((e) -> this.changeToEuro());
        this.componentPanel.add(this.euroButton, componentPanelGbc);

        this.dollarButton = new JButton(Constants.DOLLAR_BUTTON_ICON);
        componentPanelGbc.gridx = 2; // Cell 2
        componentPanelGbc.gridy = 1; // Cell 1
        componentPanelGbc.ipadx = 0; // Additional Length
        componentPanelGbc.ipady = 0; // Additional Height
        componentPanelGbc.insets = new Insets(10, 0, 0, 0);
        this.dollarButton.setBorder(null);
        this.dollarButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.dollarButton.setContentAreaFilled(false);
        this.dollarButton.setPressedIcon(Constants.DOLLAR_BUTTON_ICON_ACTIVE);
        this.dollarButton.setToolTipText("United States Dollar");
        this.dollarButton.addActionListener((e) -> this.changeToDollar());
        this.componentPanel.add(this.dollarButton, componentPanelGbc);

        this.ausDollarButton = new JButton(Constants.AUD_BUTTON_ICON);
        componentPanelGbc.gridx = 0; // Cell 0
        componentPanelGbc.gridy = 2; // Cell 2
        componentPanelGbc.ipadx = 0; // Additional Length
        componentPanelGbc.ipady = 0; // Additional Height
        componentPanelGbc.insets = new Insets(50, 0, 40, 0);
        this.ausDollarButton.setBorder(null);
        this.ausDollarButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.ausDollarButton.setContentAreaFilled(false);
        this.ausDollarButton.setPressedIcon(Constants.AUD_BUTTON_ICON_ACTIVE);
        this.ausDollarButton.setToolTipText("Australian Dollar");
        this.ausDollarButton.addActionListener((e) -> this.changeToAud());
        this.componentPanel.add(this.ausDollarButton, componentPanelGbc);

        this.liraButton = new JButton(Constants.LIRA_BUTTON_ICON);
        componentPanelGbc.gridx = 1; // Cell 1
        componentPanelGbc.gridy = 2; // Cell 2
        componentPanelGbc.ipadx = 0; // Additional Length
        componentPanelGbc.ipady = 0; // Additional Height
        componentPanelGbc.insets = new Insets(50, 0, 40, 0);
        this.liraButton.setBorder(null);
        this.liraButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.liraButton.setContentAreaFilled(false);
        this.liraButton.setPressedIcon(Constants.LIRA_BUTTON_ICON_ACTIVE);
        this.liraButton.setToolTipText("Turkish Lira");
        this.liraButton.addActionListener((e) -> this.changeToLira());
        this.componentPanel.add(this.liraButton, componentPanelGbc);

        this.zlotyButton = new JButton(Constants.ZLOTY_BUTTON_ICON);
        componentPanelGbc.gridx = 2; // Cell 2
        componentPanelGbc.gridy = 2; // Cell 2
        componentPanelGbc.ipadx = 0; // Additional Length
        componentPanelGbc.ipady = 0; // Additional Height
        componentPanelGbc.insets = new Insets(50, 0, 40, 0);
        this.zlotyButton.setBorder(null);
        this.zlotyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.zlotyButton.setContentAreaFilled(false);
        this.zlotyButton.setPressedIcon(Constants.ZLOTY_BUTTON_ICON_ACTIVE);
        this.zlotyButton.setToolTipText("Polish Zloty");
        this.zlotyButton.addActionListener((e) -> this.changeToZloty());
        this.componentPanel.add(this.zlotyButton, componentPanelGbc);

        JSeparator s1 = new JSeparator(JSeparator.HORIZONTAL);
        componentPanelGbc.gridx = 0; // Cell 0
        componentPanelGbc.gridy = 3; // Cell 3
        componentPanelGbc.ipadx = 550; // Additional Length
        componentPanelGbc.ipady = 0; // Additional Height
        componentPanelGbc.gridwidth = 3; // Span 3 cells
        componentPanelGbc.insets = new Insets(10, 0, 0, 0);
        s1.setBackground(Constants.GREY);
        s1.setForeground(Constants.GREY);
        this.componentPanel.add(s1, componentPanelGbc);

        this.updateWalletNameTitleLabel = new JLabel("Update Wallet Name");
        componentPanelGbc.gridx = 1; // Cell 1
        componentPanelGbc.gridy = 4; // Cell 4
        componentPanelGbc.ipadx = 0; // Additional Length
        componentPanelGbc.ipady = 0; // Additional Height
        componentPanelGbc.gridwidth = 1; // Default Value
        componentPanelGbc.insets = new Insets(40, 0, 10, 0);
        this.updateWalletNameTitleLabel.setForeground(Constants.GREY);
        this.updateWalletNameTitleLabel.setFont(new Font("consolas", Font.BOLD, 30));
        this.componentPanel.add(this.updateWalletNameTitleLabel, componentPanelGbc);

        this.updateWalletNameField = new JTextField(21);
        componentPanelGbc.gridx = 1; // Cell 1
        componentPanelGbc.gridy = 5; // Cell 5
        componentPanelGbc.ipadx = 0; // Additional Length
        componentPanelGbc.ipady = 0; // Additional Height
        componentPanelGbc.gridwidth = 1; // Default Value
        componentPanelGbc.insets = new Insets(10, 0, 10, 0);
        this.updateWalletNameField.setBorder(null);
        this.updateWalletNameField.putClientProperty("caretWidth", 3);
        this.updateWalletNameField.setText(this.wm.loggedInWallet);
        this.componentPanel.add(this.updateWalletNameField, componentPanelGbc);

        this.updateWalletNameButton = new JButton(Constants.UPDATE_NAME_BUTTON_ICON);
        componentPanelGbc.gridx = 1; // Cell 1
        componentPanelGbc.gridy = 6; // Cell 6
        componentPanelGbc.ipadx = 0; // Additional Length
        componentPanelGbc.ipady = 0; // Additional Height
        componentPanelGbc.gridwidth = 1; // Default Value
        componentPanelGbc.insets = new Insets(25, 0, 40, 0);
        this.updateWalletNameButton.setBorder(null);
        this.updateWalletNameButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.updateWalletNameButton.setContentAreaFilled(false);
        this.updateWalletNameButton.setPressedIcon(Constants.UPDATE_NAME_BUTTON_ICON_ACTIVE);
        this.updateWalletNameButton.setToolTipText("Update Wallet Name");
        this.updateWalletNameButton.addActionListener((e) -> this.changeWalletName());
        this.componentPanel.add(this.updateWalletNameButton, componentPanelGbc);

        JSeparator s2 = new JSeparator(JSeparator.HORIZONTAL);
        componentPanelGbc.gridx = 0; // Cell 0
        componentPanelGbc.gridy = 7; // Cell 7
        componentPanelGbc.ipadx = 550; // Additional Length
        componentPanelGbc.ipady = 0; // Additional Height
        componentPanelGbc.gridwidth = 3; // Span 3 cells
        componentPanelGbc.insets = new Insets(10, 0, 0, 0);
        s2.setBackground(Constants.GREY);
        s2.setForeground(Constants.GREY);
        this.componentPanel.add(s2, componentPanelGbc);

        this.viewRecoveryTitleLabel = new JLabel("View Recovery Phrase");
        componentPanelGbc.gridx = 1; // Cell 1
        componentPanelGbc.gridy = 8; // Cell 8
        componentPanelGbc.ipadx = 0; // Additional Length
        componentPanelGbc.ipady = 0; // Additional Height
        componentPanelGbc.gridwidth = 1; // Default Value
        componentPanelGbc.insets = new Insets(40, 0, 10, 0);
        this.viewRecoveryTitleLabel.setForeground(Constants.GREY);
        this.viewRecoveryTitleLabel.setFont(new Font("consolas", Font.BOLD, 30));
        this.componentPanel.add(this.viewRecoveryTitleLabel, componentPanelGbc);

        this.viewRecoveryButton = new JButton(Constants.VIEW_RECOVERY_PHRASE_BUTTON_ICON);
        componentPanelGbc.gridx = 1; // Cell 1
        componentPanelGbc.gridy = 9; // Cell 9
        componentPanelGbc.ipadx = 0; // Additional Length
        componentPanelGbc.ipady = 0; // Additional Height
        componentPanelGbc.gridwidth = 1; // Default Value
        componentPanelGbc.insets = new Insets(10, 0, 40, 0);
        this.viewRecoveryButton.setBorder(null);
        this.viewRecoveryButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.viewRecoveryButton.setContentAreaFilled(false);
        this.viewRecoveryButton.setPressedIcon(Constants.VIEW_RECOVERY_PHRASE_BUTTON_ICON_ACTIVE);
        this.viewRecoveryButton.setToolTipText("View Recovery Phrase");
        this.viewRecoveryButton.addActionListener((e) -> this.viewRecoveryPhrase());
        this.componentPanel.add(this.viewRecoveryButton, componentPanelGbc);

        JSeparator s3 = new JSeparator(JSeparator.HORIZONTAL);
        componentPanelGbc.gridx = 0; // Cell 0
        componentPanelGbc.gridy = 10; // Cell 10
        componentPanelGbc.ipadx = 550; // Additional Length
        componentPanelGbc.ipady = 0; // Additional Height
        componentPanelGbc.gridwidth = 3; // Span 3 cells
        componentPanelGbc.insets = new Insets(10, 0, 0, 0);
        s3.setBackground(Constants.GREY);
        s3.setForeground(Constants.GREY);
        this.componentPanel.add(s3, componentPanelGbc);

        this.deleteWalletTitleLabel = new JLabel("Delete Wallet");
        componentPanelGbc.gridx = 1; // Cell 1
        componentPanelGbc.gridy = 11; // Cell 11
        componentPanelGbc.ipadx = 0; // Additional Length
        componentPanelGbc.ipady = 0; // Additional Height
        componentPanelGbc.gridwidth = 1; // Default Value
        componentPanelGbc.insets = new Insets(40, 0, 10, 0);
        this.deleteWalletTitleLabel.setForeground(Constants.GREY);
        this.deleteWalletTitleLabel.setFont(new Font("consolas", Font.BOLD, 30));
        this.componentPanel.add(this.deleteWalletTitleLabel, componentPanelGbc);

        this.deleteWalletButton = new JButton(Constants.DELETE_WALLET_BUTTON_ICON);
        componentPanelGbc.gridx = 1; // Cell 1
        componentPanelGbc.gridy = 12; // Cell 12
        componentPanelGbc.ipadx = 0; // Additional Length
        componentPanelGbc.ipady = 0; // Additional Height
        componentPanelGbc.gridwidth = 1; // Default Value
        componentPanelGbc.insets = new Insets(10, 0, 0, 0);
        this.deleteWalletButton.setBorder(null);
        this.deleteWalletButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.deleteWalletButton.setContentAreaFilled(false);
        this.deleteWalletButton.setPressedIcon(Constants.DELETE_WALLET_BUTTON_ICON_ACTIVE);
        this.deleteWalletButton.setToolTipText("Delete Wallet");
        this.deleteWalletButton.addActionListener((e) -> this.deleteWallet());
        this.componentPanel.add(this.deleteWalletButton, componentPanelGbc);

        this.scrollPane = new JScrollPane(this.componentPanel);
        this.gbc.gridx = 0; // Cell 0
        this.gbc.gridy = 0; // Cell 0
        this.gbc.ipadx = 750; // Additional Length
        this.gbc.ipady = 550; // Additional Height
        this.gbc.insets = new Insets(0, 0, 0, 0);
        this.scrollPane.getVerticalScrollBar().setUI(this.getCustomScrollBarUI());
        this.scrollPane.getVerticalScrollBar().setBackground(Constants.DARK_GREY);
        this.scrollPane.getVerticalScrollBar().setUnitIncrement(10); // Feels best when scrolling
        this.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.scrollPane.setBorder(null);
        this.add(this.scrollPane, this.gbc);
    }

    /**
     * Creates new custom scrollbar UI.
     * @return Custom BasicScrollbarUI object.
     */
    private BasicScrollBarUI getCustomScrollBarUI(){
        return new BasicScrollBarUI(){
            @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                return button;
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                return button;
            }

            @Override
            protected void configureScrollBarColors(){
                this.thumbColor = Constants.GREY;
            }
        };
    }

    /**
     * Changes application currency
     * to the Great British Pound.
     */
    private void changeToPound(){
        this.dp.currentPair = "BTCGBP";
        this.poundButton.setIcon(Constants.POUND_BUTTON_ICON_ACTIVE);
        this.euroButton.setIcon(Constants.EURO_BUTTON_ICON);
        this.dollarButton.setIcon(Constants.DOLLAR_BUTTON_ICON);
        this.ausDollarButton.setIcon(Constants.AUD_BUTTON_ICON);
        this.liraButton.setIcon(Constants.LIRA_BUTTON_ICON);
        this.zlotyButton.setIcon(Constants.ZLOTY_BUTTON_ICON);
    }

    /**
     * Changes application currency
     * to the Euro.
     */
    private void changeToEuro(){
        this.dp.currentPair = "BTCEUR";
        this.euroButton.setIcon(Constants.EURO_BUTTON_ICON_ACTIVE);
        this.poundButton.setIcon(Constants.POUND_BUTTON_ICON);
        this.dollarButton.setIcon(Constants.DOLLAR_BUTTON_ICON);
        this.ausDollarButton.setIcon(Constants.AUD_BUTTON_ICON);
        this.liraButton.setIcon(Constants.LIRA_BUTTON_ICON);
        this.zlotyButton.setIcon(Constants.ZLOTY_BUTTON_ICON);
    }

    /**
     * Changes application currency
     * to the United States Dollar.
     */
    private void changeToDollar(){
        this.dp.currentPair = "BTCUSDT";
        this.dollarButton.setIcon(Constants.DOLLAR_BUTTON_ICON_ACTIVE);
        this.poundButton.setIcon(Constants.POUND_BUTTON_ICON);
        this.euroButton.setIcon(Constants.EURO_BUTTON_ICON);
        this.ausDollarButton.setIcon(Constants.AUD_BUTTON_ICON);
        this.liraButton.setIcon(Constants.LIRA_BUTTON_ICON);
        this.zlotyButton.setIcon(Constants.ZLOTY_BUTTON_ICON);
    }

    /**
     * Changes application currency
     * to the Australian Dollar.
     */
    private void changeToAud(){
        this.dp.currentPair = "BTCAUD";
        this.ausDollarButton.setIcon(Constants.AUD_BUTTON_ICON_ACTIVE);
        this.poundButton.setIcon(Constants.POUND_BUTTON_ICON);
        this.euroButton.setIcon(Constants.EURO_BUTTON_ICON);
        this.dollarButton.setIcon(Constants.DOLLAR_BUTTON_ICON);
        this.liraButton.setIcon(Constants.LIRA_BUTTON_ICON);
        this.zlotyButton.setIcon(Constants.ZLOTY_BUTTON_ICON);
    }

    /**
     * Changes application currency
     * to the Turkish Lira.
     */
    private void changeToLira(){
        this.dp.currentPair = "BTCTRY";
        this.liraButton.setIcon(Constants.LIRA_BUTTON_ICON_ACTIVE);
        this.poundButton.setIcon(Constants.POUND_BUTTON_ICON);
        this.euroButton.setIcon(Constants.EURO_BUTTON_ICON);
        this.dollarButton.setIcon(Constants.DOLLAR_BUTTON_ICON);
        this.ausDollarButton.setIcon(Constants.AUD_BUTTON_ICON);
        this.zlotyButton.setIcon(Constants.ZLOTY_BUTTON_ICON);
    }

    /**
     * Changes application currency
     * to the Polish Zloty.
     */
    private void changeToZloty(){
        this.dp.currentPair = "BTCPLN";
        this.zlotyButton.setIcon(Constants.ZLOTY_BUTTON_ICON_ACTIVE);
        this.poundButton.setIcon(Constants.POUND_BUTTON_ICON);
        this.euroButton.setIcon(Constants.EURO_BUTTON_ICON);
        this.dollarButton.setIcon(Constants.DOLLAR_BUTTON_ICON);
        this.ausDollarButton.setIcon(Constants.AUD_BUTTON_ICON);
        this.liraButton.setIcon(Constants.LIRA_BUTTON_ICON);
    }

    /**
     * Updates the currently logged in wallet's
     * name to the new one provided. If the new
     * wallet name provided is the same as the
     * currently logged in wallet's name, it
     * will not allow the wallet name to be
     * updated.
     */
    private void changeWalletName(){
        String newWalletName = this.updateWalletNameField.getText();
        String msg;

        if(newWalletName.equals(this.wm.loggedInWallet)){
            msg = "New wallet name must be different from current wallet name.";
            JOptionPane.showMessageDialog(this, msg, "Invalid Wallet Name", JOptionPane.ERROR_MESSAGE);
            this.updateWalletNameField.setText(this.wm.loggedInWallet);
        }
        else if(newWalletName.length() > 20){
            msg = "New wallet name must be 20 characters or less.";
            JOptionPane.showMessageDialog(this, msg, "Invalid Wallet Name", JOptionPane.ERROR_MESSAGE);
            this.updateWalletNameField.setText(this.wm.loggedInWallet);
        }
        else if(newWalletName.length() == 0 || newWalletName.strip().length() == 0){
            msg = "New wallet name must include at least 1 character.";
            JOptionPane.showMessageDialog(this, msg, "Invalid Wallet Name", JOptionPane.ERROR_MESSAGE);
            this.updateWalletNameField.setText(this.wm.loggedInWallet);
        }
        else{
            this.wm.updateWalletName(this.wm.loggedInWallet, newWalletName);
            this.dp.loggedInWalletLabel.setText(this.wm.loggedInWallet);
            this.updateWalletNameField.setText(this.wm.loggedInWallet);
        }
    }

    /**
     * Shows the currently logged in wallet's
     * 12 word recovery phrase with option to
     * export the phrase to a file anywhere on
     * disk.
     */
    private void viewRecoveryPhrase(){
        JFrame f = new JFrame("Recovery Phrase Viewer");
        f.setIconImage(Constants.WINDOW_ICON);
        f.setSize(new Dimension(400, 200));
        f.setLayout(new GridBagLayout());
        f.setLocationRelativeTo(null);
        f.setResizable(false);

        GridBagConstraints gbc = new GridBagConstraints();

        JLabel pl = new JLabel("Enter Wallet Password");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 20, 0);
        pl.setForeground(Constants.ENSYNC_BLUE);
        f.add(pl, gbc);

        JPasswordField pf = new JPasswordField(20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 20, 0);
        pf.setBorder(null);
        pf.putClientProperty("caretWidth", 3);
        f.add(pf, gbc);

        JButton vb = new JButton(Constants.VIEW_BUTTON_ICON);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        vb.setBorder(null);
        vb.setCursor(new Cursor(Cursor.HAND_CURSOR));
        vb.setContentAreaFilled(false);
        vb.setPressedIcon(Constants.VIEW_BUTTON_ICON_ACTIVE);
        vb.setToolTipText("View Phrase");
        vb.addActionListener((e) -> {
            String password = new String(pf.getPassword());
            if(password.equals("")){
                JOptionPane.showMessageDialog(null, "Enter password.", "Missing Password", JOptionPane.ERROR_MESSAGE);
            }
            else{
                if(this.wm.viewPhrase(this.wm.loggedInWallet, password)){
                    f.getContentPane().removeAll();
                    f.revalidate();
                    f.repaint();
                    f.setSize(new Dimension(1000, 200));
                    f.setLocationRelativeTo(null);

                    JLabel tl = new JLabel("12 Word Phrase");
                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    gbc.insets = new Insets(0, 0, 20, 0);
                    tl.setForeground(Constants.ENSYNC_BLUE);
                    f.add(tl, gbc);

                    JLabel rpl = new JLabel(this.wm.getBitcoin().getMnemonicPhrase());
                    gbc.gridx = 0;
                    gbc.gridy = 1;
                    gbc.insets = new Insets(0, 0, 20, 0);
                    rpl.setForeground(Constants.ENSYNC_BLUE);
                    f.add(rpl, gbc);

                    JButton eb = new JButton(Constants.EXPORT_BUTTON_ICON);
                    gbc.gridx = 0;
                    gbc.gridy = 2;
                    gbc.insets = new Insets(0, 0, 0, 0);
                    eb.setBorder(null);
                    eb.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    eb.setContentAreaFilled(false);
                    eb.setPressedIcon(Constants.EXPORT_BUTTON_ICON_ACTIVE);
                    eb.setToolTipText("Export Phrase");
                    eb.addActionListener((e2) -> {
                        JFileChooser fc = new JFileChooser(System.getenv("SystemDrive"));
                        if(fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){
                            try(FileWriter fw = new FileWriter(fc.getSelectedFile() + ".txt")){
                                fw.write("12 Word Recovery Phrase for " + this.wm.loggedInWallet + "\n" + this.wm.getBitcoin().getMnemonicPhrase());
                            }
                            catch(IOException e3){
                                JOptionPane.showMessageDialog(this, "Save Failed", "Save Failed", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        f.dispose();
                    });
                    f.add(eb, gbc);

                    f.revalidate();
                    f.repaint();
                }
                else{
                    this.wm.clearWalletData(true, true, 50);
                    f.dispose();
                    this.af.dispose();
                    new LoginFrame();
                    String msg = "You have been logged out due to an incorrect password provided." +
                            "\nIf this was you, log back into your wallet.";
                    JOptionPane.showMessageDialog(this, msg, "Logged Out", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        f.add(vb, gbc);
        f.setVisible(true);
    }

    /**
     * Deletes currently logged in wallet
     * if OK option is selected, cancels otherwise.
     */
    private void deleteWallet(){
        String msg = "Are you sure you would like to permanently delete " + this.wm.loggedInWallet + "?";
        if(JOptionPane.showConfirmDialog(this, msg, "Confirm Wallet Deletion", JOptionPane.YES_NO_OPTION) == 0){
            this.wm.deleteWallet(this.wm.loggedInWallet);
            this.af.dispose();
            new LoginFrame();
        }
    }
}
