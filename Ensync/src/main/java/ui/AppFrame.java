package ui;

import utils.Constants;
import utils.WalletManager;

import javax.swing.*;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class AppFrame extends JFrame {

    // WALLET MANAGER
    private final WalletManager wm;

    // NO INTERNET PANEL
    private final JPanel noInternetPanel;

    // CENTRED BASED PANEL (CONTAINS PANELS TO SWITCH)
    private final JPanel centredBasePanel;

    // NAVIGATION BAR
    private final NavigationBar navigationBar;

    // DASHBOARD PANEL
    final DashboardPanel dashboardPanel;

    // SEND FUNDS PANEL
    final SendFundsPanel sendFundsPanel;

    // RECEIVE PANEL
    final ReceiveFundsPanel receiveFundsPanel;

    // SETTINGS PANEL
    final SettingsPanel settingsPanel;

    /**
     * Constructs an AppFrame object.
     * @param wm Wallet manager instance.
     */
    public AppFrame(WalletManager wm){
        super(Constants.WINDOW_TITLE);
        this.setIconImage(Constants.WINDOW_ICON);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setSize(Constants.APP_WINDOW_DIMENSION);
        this.setMinimumSize(Constants.MINIMUM_APP_WINDOW_DIMENSION);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        // Delete wallet data after closing frame.
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                wm.clearWalletData(true, true, 50);
            }
        });

        // WALLET MANAGER INSTANCE
        this.wm = wm;

        this.noInternetPanel = new JPanel();
        this.noInternetPanel.setBackground(Constants.DARK_RED);
        this.noInternetPanel.setPreferredSize(new Dimension(0, 30));
        this.noInternetPanel.setVisible(false);
        JLabel noInternetLabel = new JLabel("No Internet Available | Check Your Internet Connection");
        noInternetLabel.setForeground(Constants.WHITE);
        noInternetPanel.add(noInternetLabel);
        this.add(noInternetPanel, BorderLayout.SOUTH);

        this.navigationBar = new NavigationBar(this.wm, this);
        this.add(this.navigationBar, BorderLayout.WEST);

        // PANEL ONLY FOR CONTAINING PANELS TO SWITCH
        this.centredBasePanel = new JPanel(new GridBagLayout());
        GridBagConstraints basePanelConstraints = new GridBagConstraints();

        this.dashboardPanel = new DashboardPanel(this.wm);
        basePanelConstraints.gridx = 0;
        basePanelConstraints.gridy = 0;
        basePanelConstraints.ipadx = 0;
        basePanelConstraints.ipady = 0;
        basePanelConstraints.insets = new Insets(0, 0, 0, 0);
        this.centredBasePanel.add(this.dashboardPanel, basePanelConstraints);

        this.sendFundsPanel = new SendFundsPanel(this.wm);
        basePanelConstraints.gridx = 0;
        basePanelConstraints.gridy = 0;
        basePanelConstraints.ipadx = 0;
        basePanelConstraints.ipady = 0;
        basePanelConstraints.insets = new Insets(0, 0, 0, 0);
        this.sendFundsPanel.setVisible(false);
        this.centredBasePanel.add(this.sendFundsPanel, basePanelConstraints);

        this.receiveFundsPanel = new ReceiveFundsPanel(this.wm);
        basePanelConstraints.gridx = 0;
        basePanelConstraints.gridy = 0;
        basePanelConstraints.ipadx = 0;
        basePanelConstraints.ipady = 0;
        basePanelConstraints.insets = new Insets(0, 0, 0, 0);
        this.receiveFundsPanel.setVisible(false);
        this.centredBasePanel.add(this.receiveFundsPanel, basePanelConstraints);

        this.settingsPanel = new SettingsPanel(this.wm, this, this.dashboardPanel);
        basePanelConstraints.gridx = 0;
        basePanelConstraints.gridy = 0;
        basePanelConstraints.ipadx = 0;
        basePanelConstraints.ipady = 0;
        basePanelConstraints.insets = new Insets(0, 0, 0, 0);
        this.settingsPanel.setVisible(false);
        this.centredBasePanel.add(this.settingsPanel, basePanelConstraints);

        this.checkInternet();
        this.add(this.centredBasePanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    /**
     * Displays a panel indicating
     * that there is no internet
     * connection.
     */
    @SuppressWarnings("BusyWait")
    private void checkInternet(){
        Thread t = new Thread(() -> {
            // Ensure single failed ping does
            // not cause panel to show.
            byte failedPings = 0;

            while(true){
                if(!isInternetActive()) {
                    // 3 consecutive failed pings probably no internet
                    if(failedPings == 3){
                        noInternetPanel.setVisible(true);
                        dashboardPanel.walletBalanceLabel.setText("----");
                        dashboardPanel.bitcoinPriceLabel.setText("----");
                        dashboardPanel.totalReceivedLabel.setText("----");
                        dashboardPanel.priceChangeLabel.setText("----");
                        dashboardPanel.priceChangePercentLabel.setText("----");
                        continue;
                    }
                    failedPings++;
                }
                else{
                    // Reset to avoid failed pings equal to
                    // 3 check always running if single
                    // ping fails.
                    failedPings = 0;
                    noInternetPanel.setVisible(false);
                }
                // Ease CPU usage.
                try {
                    Thread.sleep(300);
                }
                catch(InterruptedException ignored){}
            }
        });
        t.setPriority(Thread.NORM_PRIORITY);
        t.start();
    }

    /**
     * Checks if an internet connection is present by pinging
     * www.google.com using OS specific command and checking
     * exit code of process.
     * @return true if exit code is 0, false otherwise.
     */
    private boolean isInternetActive(){
        switch(Constants.OS_VERSION){
            case "Windows Vista":
            case "Windows 7":
            case "Windows 8.1":
            case "Windows 10":
            case "Windows 11":
                try{
                    ProcessBuilder ps = new ProcessBuilder("ping", "www.google.com", "-n", "1");
                    Process process = ps.start();
                    if(process.waitFor() == 0){
                        // if zero.
                        return true;
                    }
                }
                catch(IOException | InterruptedException ignored){}
                break;

            case "Mac OS X":
            case "Linux":
                try{
                    ProcessBuilder ps = new ProcessBuilder("ping", "www.google.com", "-c", "1");
                    Process process = ps.start();
                    if(process.waitFor() == 0){
                        return true;
                    }
                }
                catch(IOException | InterruptedException ignored){}
                break;
        }
        // if non zero.
        return false;
    }
}
