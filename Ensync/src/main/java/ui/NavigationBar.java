package ui;

import utils.Constants;
import utils.WalletManager;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class NavigationBar extends JPanel {

    // COMPONENT CONSTRAINTS INSTANCE
    private final GridBagConstraints gbc = new GridBagConstraints();

    // WALLET MANAGER
    private final WalletManager wm;

    // APP FRAME
    private final AppFrame app;

    // ACCOUNT BUTTON
    private final JButton dashboardButton;

    // SEND FUNDS BUTTON
    private final JButton sendFundsButton;

    // RECEIVE FUNDS BUTTON
    private final JButton receiveFundsButton;

    // SETTINGS BUTTON
    private final JButton settingsButton;

    // LOGOUT BUTTON
    private final JButton logoutButton;

    /**
     * Constructs a NavigationBar object.
     * @param wm Wallet manager instance.
     * @param app App frame instance.
     */
    public NavigationBar(WalletManager wm, AppFrame app){
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(152, 0));
        this.setBackground(Constants.DARK_GREY);
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, Constants.ENSYNC_BLUE));

        // WALLET MANAGER INSTANCE
        this.wm = wm;

        // APP INSTANCE
        this.app = app;

        this.dashboardButton = new JButton(Constants.DASHBOARD_BUTTON_ICON);
        this.gbc.gridx = 0; // Cell 0
        this.gbc.gridy = 0; // Cell 0
        this.gbc.ipadx = 60; // Additional Length
        this.gbc.ipady = 60; // Additional Height
        this.gbc.insets = new Insets(0, 0, 0, 0);
        this.dashboardButton.setBackground(Constants.DARK_GREY);
        this.dashboardButton.setFocusable(false);
        this.dashboardButton.setBorder(null);
        this.dashboardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.dashboardButton.setPressedIcon(Constants.DASHBOARD_BUTTON_ICON_ACTIVE);
        this.dashboardButton.setToolTipText("Dashboard");
        this.dashboardButton.addActionListener((e) -> this.showDashboardPanel());
        this.dashboardButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                onHoverChangeColour(true, dashboardButton);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                onHoverChangeColour(false, dashboardButton);
            }
        });
        this.add(this.dashboardButton, this.gbc);

        this.sendFundsButton = new JButton(Constants.SEND_FUNDS_BUTTON_ICON);
        this.gbc.gridx = 0; // Cell 0
        this.gbc.gridy = 1; // Cell 1
        this.gbc.ipadx = 60; // Additional Length
        this.gbc.ipady = 60; // Additional Height
        this.gbc.insets = new Insets(0, 0, 0, 0);
        this.sendFundsButton.setBackground(Constants.DARK_GREY);
        this.sendFundsButton.setFocusable(false);
        this.sendFundsButton.setBorder(null);
        this.sendFundsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.sendFundsButton.setPressedIcon(Constants.SEND_FUNDS_BUTTON_ICON_ACTIVE);
        this.sendFundsButton.setToolTipText("Send Funds");
        this.sendFundsButton.addActionListener((e) -> this.showSendFundsPanel());
        this.sendFundsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                onHoverChangeColour(true, sendFundsButton);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                onHoverChangeColour(false, sendFundsButton);
            }
        });
        this.add(this.sendFundsButton, this.gbc);

        this.receiveFundsButton = new JButton(Constants.RECEIVE_FUNDS_BUTTON_ICON);
        this.gbc.gridx = 0; // Cell 0
        this.gbc.gridy = 2; // Cell 2
        this.gbc.ipadx = 60; // Additional Length
        this.gbc.ipady = 60; // Additional Height
        this.gbc.insets = new Insets(0, 0, 0, 0);
        this.receiveFundsButton.setBackground(Constants.DARK_GREY);
        this.receiveFundsButton.setFocusable(false);
        this.receiveFundsButton.setBorder(null);
        this.receiveFundsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.receiveFundsButton.setPressedIcon(Constants.RECEIVE_FUNDS_BUTTON_ICON_ACTIVE);
        this.receiveFundsButton.setToolTipText("Receive Funds");
        this.receiveFundsButton.addActionListener((e) -> this.showReceiveFundsPanel());
        this.receiveFundsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                onHoverChangeColour(true, receiveFundsButton);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                onHoverChangeColour(false, receiveFundsButton);
            }
        });
        this.add(this.receiveFundsButton, this.gbc);

        this.settingsButton = new JButton(Constants.SETTINGS_BUTTON_ICON);
        this.gbc.gridx = 0; // Cell 0
        this.gbc.gridy = 3; // Cell 3
        this.gbc.ipadx = 60; // Additional Length
        this.gbc.ipady = 60; // Additional Height
        this.gbc.insets = new Insets(0, 0, 0, 0);
        this.settingsButton.setBackground(Constants.DARK_GREY);
        this.settingsButton.setFocusable(false);
        this.settingsButton.setBorder(null);
        this.settingsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.settingsButton.setPressedIcon(Constants.SETTINGS_BUTTON_ICON_ACTIVE);
        this.settingsButton.setToolTipText("Settings");
        this.settingsButton.addActionListener((e) -> this.showSettingsPanel());
        this.settingsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                onHoverChangeColour(true, settingsButton);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                onHoverChangeColour(false, settingsButton);
            }
        });
        this.add(this.settingsButton, this.gbc);

        this.logoutButton = new JButton(Constants.LOGOUT_BUTTON_ICON);
        this.gbc.gridx = 0; // Cell 0
        this.gbc.gridy = 4; // Cell 4
        this.gbc.ipadx = 60; // Additional Length
        this.gbc.ipady = 60; // Additional Height
        this.gbc.insets = new Insets(0, 0, 0, 0);
        this.logoutButton.setBackground(Constants.DARK_GREY);
        this.logoutButton.setFocusable(false);
        this.logoutButton.setBorder(null);
        this.logoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.logoutButton.setPressedIcon(Constants.LOGOUT_BUTTON_ICON_ACTIVE);
        this.logoutButton.setToolTipText("Logout");
        this.logoutButton.addActionListener((e) -> this.logout());
        this.logoutButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                onHoverChangeColour(true, logoutButton);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                onHoverChangeColour(false, logoutButton);
            }
        });
        this.add(this.logoutButton, this.gbc);
    }

    /**
     * Changes JButton's colour to indicate
     * the mouse is hovering over the JButton.
     * @param isHovered Whether cursor is over JButton.
     * @param button The JButton to change colour of.
     */
    private void onHoverChangeColour(boolean isHovered, JButton button){
        if(isHovered){
            button.setBackground(Constants.GREY);
        }
        else{
            button.setBackground(Constants.DARK_GREY);
        }
    }

    /**
     * Shows the Dashboard panel and
     * hides any other visible panel.
     */
    private void showDashboardPanel(){
        this.dashboardButton.setIcon(Constants.DASHBOARD_BUTTON_ICON_ACTIVE);
        this.sendFundsButton.setIcon(Constants.SEND_FUNDS_BUTTON_ICON);
        this.receiveFundsButton.setIcon(Constants.RECEIVE_FUNDS_BUTTON_ICON);
        this.settingsButton.setIcon(Constants.SETTINGS_BUTTON_ICON);
        this.app.dashboardPanel.setVisible(true);
        this.app.sendFundsPanel.setVisible(false);
        this.app.receiveFundsPanel.setVisible(false);
        this.app.settingsPanel.setVisible(false);
    }

    /**
     * Shows the Dashboard panel and
     * hides any other visible panel.
     */
    private void showSendFundsPanel(){
        this.sendFundsButton.setIcon(Constants.SEND_FUNDS_BUTTON_ICON_ACTIVE);
        this.dashboardButton.setIcon(Constants.DASHBOARD_BUTTON_ICON);
        this.receiveFundsButton.setIcon(Constants.RECEIVE_FUNDS_BUTTON_ICON);
        this.settingsButton.setIcon(Constants.SETTINGS_BUTTON_ICON);
        this.app.sendFundsPanel.setVisible(true);
        this.app.dashboardPanel.setVisible(false);
        this.app.receiveFundsPanel.setVisible(false);
        this.app.settingsPanel.setVisible(false);
    }

    /**
     * Shows the Dashboard panel and
     * hides any other visible panel.
     */
    private void showReceiveFundsPanel(){
        this.receiveFundsButton.setIcon(Constants.RECEIVE_FUNDS_BUTTON_ICON_ACTIVE);
        this.dashboardButton.setIcon(Constants.DASHBOARD_BUTTON_ICON);
        this.sendFundsButton.setIcon(Constants.SEND_FUNDS_BUTTON_ICON);
        this.settingsButton.setIcon(Constants.SETTINGS_BUTTON_ICON);
        this.app.receiveFundsPanel.setVisible(true);
        this.app.dashboardPanel.setVisible(false);
        this.app.sendFundsPanel.setVisible(false);
        this.app.settingsPanel.setVisible(false);
    }

    /**
     * Shows the Settings panel and
     * hides any other visible panel.
     */
    private void showSettingsPanel(){
        this.settingsButton.setIcon(Constants.SETTINGS_BUTTON_ICON_ACTIVE);
        this.dashboardButton.setIcon(Constants.DASHBOARD_BUTTON_ICON);
        this.sendFundsButton.setIcon(Constants.SEND_FUNDS_BUTTON_ICON);
        this.receiveFundsButton.setIcon(Constants.RECEIVE_FUNDS_BUTTON_ICON);
        this.app.settingsPanel.setVisible(true);
        this.app.dashboardPanel.setVisible(false);
        this.app.sendFundsPanel.setVisible(false);
        this.app.receiveFundsPanel.setVisible(false);
    }

    /**
     * Logs user out of currently
     * logged in wallet if OK option
     * is selected, cancels otherwise.
     */
    private void logout(){
        String msg = "Are you sure you want to logout of " + this.wm.loggedInWallet + "?";
        if(JOptionPane.showConfirmDialog(this.app, msg, "Logout", JOptionPane.OK_CANCEL_OPTION) == 0){
            this.wm.clearWalletData(true, true, 50);
            this.app.dispose();
            new LoginFrame();
        }
    }
}
