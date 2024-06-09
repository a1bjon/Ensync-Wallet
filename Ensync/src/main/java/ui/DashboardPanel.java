package ui;

import exceptions.InvalidPairException;

import utils.Constants;
import utils.WalletManager;

import javax.swing.*;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DashboardPanel extends JPanel {

    // COMPONENT CONSTRAINTS INSTANCE
    private final GridBagConstraints gbc = new GridBagConstraints();

    // WALLET MANAGER
    private final WalletManager wm;

    // LOGGED IN WALLET TITLE LABEL
    private final JLabel loggedInWalletTitleLabel;

    // LOGGED IN WALLET LABEL
    final JLabel loggedInWalletLabel;

    // BITCOIN PRICE TITLE LABEL
    private final JLabel walletBalanceTitleLabel;

    // BITCOIN PRICE LABEL
    final JLabel walletBalanceLabel;

    // TOTAL RECEIVED TITLE LABEL
    private final JLabel totalReceivedTitleLabel;

    // TOTAL RECEIVED LABEL
    final JLabel totalReceivedLabel;

    // BITCOIN PRICE TITLE LABEL
    private final JLabel bitcoinPriceTitleLabel;

    // BITCOIN PRICE LABEL
    final JLabel bitcoinPriceLabel;

    // PRICE CHANGE TITLE LABEL
    private final JLabel priceChangeTitleLabel;

    // PRICE CHANGE LABEL
    final JLabel priceChangeLabel;

    // PRICE CHANGE PERCENT TITLE LABEL
    private final JLabel priceChangePercentTitleLabel;

    // PRICE CHANGE PERCENT LABEL
    final JLabel priceChangePercentLabel;

    // CURRENT CURRENCY PAIR
    String currentPair = "BTCGBP";

    /**
     * Constructs a DashboardPanel object.
     * @param wm Wallet manager instance.
     */
    public DashboardPanel(WalletManager wm){
        this.setLayout(new GridBagLayout());
        this.setBackground(Constants.GREY12);

        // WALLET MANAGER INSTANCE
        this.wm = wm;

        this.loggedInWalletTitleLabel = new JLabel("Wallet");
        this.gbc.gridx = 0; // Cell 0
        this.gbc.gridy = 0; // Cell 0
        this.gbc.ipadx = 0; // Additional Length
        this.gbc.ipady = 0; // Additional Height
        this.gbc.insets = new Insets(0, 0, 0, 100);
        this.loggedInWalletTitleLabel.setForeground(Constants.GREY);
        this.loggedInWalletTitleLabel.setFont(new Font("consolas", Font.BOLD, 40));
        this.add(this.loggedInWalletTitleLabel, this.gbc);

        this.loggedInWalletLabel = new JLabel(this.wm.loggedInWallet);
        this.gbc.gridx = 0; // Cell 0
        this.gbc.gridy = 1; // Cell 1
        this.gbc.ipadx = 0; // Additional Length
        this.gbc.ipady = 0; // Additional Height
        this.gbc.insets = new Insets(0, 0, 50, 100);
        this.loggedInWalletLabel.setForeground(Constants.ENSYNC_BLUE);
        this.loggedInWalletLabel.setFont(new Font("consolas", Font.PLAIN, 40));
        this.add(this.loggedInWalletLabel, this.gbc);

        this.walletBalanceTitleLabel = new JLabel("Balance");
        this.gbc.gridx = 1; // Cell 1
        this.gbc.gridy = 0; // Cell 0
        this.gbc.ipadx = 0; // Additional Length
        this.gbc.ipady = 0; // Additional Height
        this.gbc.insets = new Insets(0, 0, 0, 0);
        this.walletBalanceTitleLabel.setForeground(Constants.GREY);
        this.walletBalanceTitleLabel.setFont(new Font("consolas", Font.BOLD, 40));
        this.add(this.walletBalanceTitleLabel, this.gbc);

        this.walletBalanceLabel = new JLabel("----");
        this.gbc.gridx = 1; // Cell 1
        this.gbc.gridy = 1; // Cell 1
        this.gbc.ipadx = 0; // Additional Length
        this.gbc.ipady = 0; // Additional Height
        this.gbc.insets = new Insets(0, 0, 50, 0);
        this.walletBalanceLabel.setForeground(Constants.ENSYNC_BLUE);
        this.walletBalanceLabel.setFont(new Font("consolas", Font.PLAIN, 40));
        this.updateBalance();
        this.add(this.walletBalanceLabel, this.gbc);

        this.totalReceivedTitleLabel = new JLabel("Received");
        this.gbc.gridx = 2; // Cell 2
        this.gbc.gridy = 0; // Cell 0
        this.gbc.ipadx = 0; // Additional Length
        this.gbc.ipady = 0; // Additional Height
        this.gbc.insets = new Insets(0, 100, 0, 0);
        this.totalReceivedTitleLabel.setForeground(Constants.GREY);
        this.totalReceivedTitleLabel.setFont(new Font("consolas", Font.BOLD, 40));
        this.add(this.totalReceivedTitleLabel, this.gbc);

        this.totalReceivedLabel = new JLabel("----");
        this.gbc.gridx = 2; // Cell 2
        this.gbc.gridy = 1; // Cell 1
        this.gbc.ipadx = 0; // Additional Length
        this.gbc.ipady = 0; // Additional Height
        this.gbc.insets = new Insets(0, 100, 50, 0);
        this.totalReceivedLabel.setForeground(Constants.ENSYNC_BLUE);
        this.totalReceivedLabel.setFont(new Font("consolas", Font.PLAIN, 40));
        this.updateTotalReceived();
        this.add(this.totalReceivedLabel, this.gbc);

        this.bitcoinPriceTitleLabel = new JLabel("BTC Price");
        this.gbc.gridx = 0; // Cell 0
        this.gbc.gridy = 2; // Cell 2
        this.gbc.ipadx = 0; // Additional Length
        this.gbc.ipady = 0; // Additional Height
        this.gbc.insets = new Insets(0, 0, 0, 100);
        this.bitcoinPriceTitleLabel.setForeground(Constants.GREY);
        this.bitcoinPriceTitleLabel.setFont(new Font("consolas", Font.BOLD, 40));
        this.add(this.bitcoinPriceTitleLabel, this.gbc);

        this.bitcoinPriceLabel = new JLabel("----");
        this.gbc.gridx = 0; // Cell 0
        this.gbc.gridy = 3; // Cell 3
        this.gbc.ipadx = 0; // Additional Length
        this.gbc.ipady = 0; // Additional Height
        this.gbc.insets = new Insets(0, 0, 0, 100);
        this.bitcoinPriceLabel.setForeground(Constants.ENSYNC_BLUE);
        this.bitcoinPriceLabel.setFont(new Font("consolas", Font.PLAIN, 40));
        try {
            this.updateBitcoinPrice();
        }
        catch(InvalidPairException e){
            this.currentPair = "BTCGBP";
        }
        this.add(this.bitcoinPriceLabel, this.gbc);

        this.priceChangeTitleLabel = new JLabel("24H Change");
        this.gbc.gridx = 1; // Cell 1
        this.gbc.gridy = 2; // Cell 2
        this.gbc.ipadx = 0; // Additional Length
        this.gbc.ipady = 0; // Additional Height
        this.gbc.insets = new Insets(0, 0, 0, 0);
        this.priceChangeTitleLabel.setForeground(Constants.GREY);
        this.priceChangeTitleLabel.setFont(new Font("consolas", Font.BOLD, 40));
        this.add(this.priceChangeTitleLabel, this.gbc);

        this.priceChangeLabel = new JLabel("----");
        this.gbc.gridx = 1; // Cell 1
        this.gbc.gridy = 3; // Cell 3
        this.gbc.ipadx = 0; // Additional Length
        this.gbc.ipady = 0; // Additional Height
        this.gbc.insets = new Insets(0, 0, 0, 0);
        this.priceChangeLabel.setForeground(Constants.ENSYNC_BLUE);
        this.priceChangeLabel.setFont(new Font("consolas", Font.PLAIN, 40));
        this.add(this.priceChangeLabel, this.gbc);

        this.priceChangePercentTitleLabel = new JLabel("24H % Change");
        this.gbc.gridx = 2; // Cell 2
        this.gbc.gridy = 2; // Cell 2
        this.gbc.ipadx = 0; // Additional Length
        this.gbc.ipady = 0; // Additional Height
        this.gbc.insets = new Insets(0, 100, 0, 0);
        this.priceChangePercentTitleLabel.setForeground(Constants.GREY);
        this.priceChangePercentTitleLabel.setFont(new Font("consolas", Font.BOLD, 40));
        this.add(this.priceChangePercentTitleLabel, this.gbc);

        this.priceChangePercentLabel = new JLabel("----");
        this.gbc.gridx = 2; // Cell 2
        this.gbc.gridy = 3; // Cell 3
        this.gbc.ipadx = 0; // Additional Length
        this.gbc.ipady = 0; // Additional Height
        this.gbc.insets = new Insets(0, 100, 0, 0);
        this.priceChangePercentLabel.setForeground(Constants.ENSYNC_BLUE);
        this.priceChangePercentLabel.setFont(new Font("consolas", Font.PLAIN, 40));
        this.add(this.priceChangePercentLabel, this.gbc);

        try {
            this.updateDailyPriceChange();
        }
        catch(InvalidPairException e){
            this.currentPair = "BTCGBP";
        }
    }

    /**
     * Fetches the current Bitcoin price
     * via Binance, in the current
     * system currency and updates
     * BTC price label.
     * @throws InvalidPairException If provided pair is not
     * valid (BTCGBP, BTCEUR, BTCUSDT, BTCAUD, BTCTRY or BTCPLN).
     */
    @SuppressWarnings("BusyWait")
    private void updateBitcoinPrice() throws InvalidPairException{
        Map<String, String> validPairs = Map.of("BTCGBP", "£",
                                                "BTCUSDT", "$",
                                                "BTCEUR", "€",
                                                "BTCAUD", "A$",
                                                "BTCTRY", "₺",
                                                "BTCPLN", "zł");

        if(validPairs.containsKey(this.currentPair)){
            Thread t = new Thread(() -> {
                while(true){
                    try {
                        // Get request from binance api endpoint
                        HttpRequest request = HttpRequest.newBuilder()
                                .GET()
                                .uri(URI.create("https://www.binance.com/api/v3/ticker/price?symbol=" + this.currentPair))
                                .build();

                        // Response
                        HttpResponse<String> response = HttpClient.newHttpClient()
                                .send(request, HttpResponse.BodyHandlers.ofString());

                        JSONObject json = (JSONObject) new JSONParser().parse(response.body());
                        // Parse float to remove extra zeros.
                        bitcoinPriceLabel.setText(validPairs.get(this.currentPair) + Float.parseFloat((String) json.get("price")));
                    }
                    catch(IOException | ParseException | IllegalStateException | InterruptedException e) {
                        // If endpoint url malformed or no json.
                        bitcoinPriceLabel.setText("Error");
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
        else{
            throw new InvalidPairException(this.currentPair + " is not a valid pair");
        }
    }

    /**
     * Fetches current Bitcoin wallet balance
     * and updates wallet balance to latest
     * balance.
     */
    @SuppressWarnings("BusyWait")
    private void updateBalance(){
        Thread t = new Thread(() -> {
            while(true){
                this.walletBalanceLabel.setText(this.wm.getBitcoin().getBalance() + " BTC");
                // Ease CPU usage.
                try {
                    Thread.sleep(30000);
                }
                catch(InterruptedException ignored){}
            }
        });
        t.setPriority(Thread.NORM_PRIORITY);
        t.start();
    }

    /**
     * Fetches the total amount of Bitcoin
     * received to the current wallet and
     * updates the total received label.
     */
    @SuppressWarnings("BusyWait")
    private void updateTotalReceived(){
        Thread t = new Thread(() -> {
            while(true){
                this.totalReceivedLabel.setText(this.wm.getBitcoin().getTotalReceived() + " BTC");
                // Ease CPU usage.
                try {
                    Thread.sleep(30000);
                }
                catch(InterruptedException ignored){}
            }
        });
        t.setPriority(Thread.NORM_PRIORITY);
        t.start();
    }

    /**
     * Fetches the current 24-hour Bitcoin price
     * and percentage change via Binance, in the
     * current system currency and updates
     * 24 hour price and percentage labels.
     * @throws InvalidPairException If provided pair is not
     * valid (BTCGBP, BTCEUR, BTCUSDT, BTCAUD, BTCTRY or BTCPLN).
     */
    @SuppressWarnings("BusyWait")
    private void updateDailyPriceChange() throws InvalidPairException{
        Map<String, String> validPairs = Map.of("BTCGBP", "£",
                "BTCUSDT", "$",
                "BTCEUR", "€",
                "BTCAUD", "A$",
                "BTCTRY", "₺",
                "BTCPLN", "zł");

        if(validPairs.containsKey(this.currentPair)){
            Thread t = new Thread(() -> {
                while(true){
                    try {
                        // Get request from binance api endpoint
                        HttpRequest request = HttpRequest.newBuilder()
                                .GET()
                                .uri(URI.create("https://api.binance.com/api/v1/ticker/24hr?symbol=" + this.currentPair))
                                .build();

                        // Response
                        HttpResponse<String> response = HttpClient.newHttpClient()
                                .send(request, HttpResponse.BodyHandlers.ofString());

                        JSONObject json = (JSONObject) new JSONParser().parse(response.body());
                        // Parse float to remove extra zeros.
                        priceChangeLabel.setText(validPairs.get(this.currentPair) + Float.parseFloat((String) json.get("priceChange")));
                        priceChangePercentLabel.setText(Float.parseFloat((String) json.get("priceChangePercent")) + "%");
                    }
                    catch(IOException | ParseException | IllegalStateException | InterruptedException e) {
                        // If endpoint url malformed or no json.
                        priceChangeLabel.setText("Error");
                        priceChangePercentLabel.setText("Error");
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
        else{
            throw new InvalidPairException(this.currentPair + " is not a valid pair");
        }
    }
}
