package ui;

import utils.Constants;
import utils.WalletManager;

import javax.swing.*;

import java.awt.*;

public class SendFundsPanel extends JPanel {

    // COMPONENT CONSTRAINTS INSTANCE
    private final GridBagConstraints gbc = new GridBagConstraints();

    // WALLET MANAGER
    private final WalletManager wm;

    // LOGO LABEL
    private final JLabel logoLabel;

    // AMOUNT LABEL
    private final JLabel amountLabel;

    // AMOUNT FIELD
    private final JTextField amountField;

    // ENTER RECIPIENT LABEL
    private final JLabel recipientLabel;

    // RECIPIENT FIELD
    private final JTextField recipientField;

    // SEND TRANSACTION BUTTON
    private final JButton transactionButton;

    /**
     * Constructs a SendFundsPanel object.
     * @param wm Wallet manager instance.
     */
    public SendFundsPanel(WalletManager wm){
        this.setLayout(new GridBagLayout());
        this.setBackground(Constants.GREY12);

        // WALLET MANAGER INSTANCE
        this.wm = wm;

        this.logoLabel = new JLabel(Constants.ENSYNC_LOGO);
        this.gbc.gridx = 0; // Cell 0
        this.gbc.gridy = 0; // Cell 0
        this.gbc.ipadx = 0; // Additional Length
        this.gbc.ipady = 0; // Additional Height
        this.gbc.insets = new Insets(0, 0, 70, 0);
        this.add(this.logoLabel, this.gbc);

        this.amountLabel = new JLabel("BTC Amount");
        this.gbc.gridx = 0; // Cell 0
        this.gbc.gridy = 1; // Cell 1
        this.gbc.ipadx = 0; // Additional Length
        this.gbc.ipady = 0; // Additional Height
        this.gbc.insets = new Insets(0, 0, 30, 0);
        this.amountLabel.setForeground(Constants.ENSYNC_BLUE);
        this.amountLabel.setFont(new Font("consolas", Font.BOLD, 30));
        this.add(this.amountLabel, this.gbc);

        this.amountField = new JTextField(1);
        this.gbc.gridx = 0; // Cell 0
        this.gbc.gridy = 2; // Cell 2
        this.gbc.ipadx = 100; // Additional Length
        this.gbc.ipady = 0; // Additional Height
        this.gbc.insets = new Insets(0, 0, 30, 0);
        this.amountField.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Constants.ENSYNC_BLUE));
        this.amountField.putClientProperty("caretWidth", 3);
        this.amountField.setFont(new Font("consolas", Font.BOLD, 30));
        this.add(this.amountField, this.gbc);

        this.recipientLabel = new JLabel("Recipient Address");
        this.gbc.gridx = 0; // Cell 0
        this.gbc.gridy = 3; // Cell 3
        this.gbc.ipadx = 0; // Additional Length
        this.gbc.ipady = 0; // Additional Height
        this.gbc.insets = new Insets(0, 0, 30, 0);
        this.recipientLabel.setForeground(Constants.ENSYNC_BLUE);
        this.recipientLabel.setFont(new Font("consolas", Font.BOLD, 30));
        this.add(this.recipientLabel, this.gbc);

        this.recipientField = new JTextField(1);
        this.gbc.gridx = 0; // Cell 0
        this.gbc.gridy = 4; // Cell 4
        this.gbc.ipadx = 500; // Additional Length
        this.gbc.ipady = 0; // Additional Height
        this.gbc.insets = new Insets(0, 0, 30, 0);
        this.recipientField.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Constants.ENSYNC_BLUE));
        this.recipientField.putClientProperty("caretWidth", 3);
        this.recipientField.setFont(new Font("consolas", Font.BOLD, 30));
        this.add(this.recipientField, this.gbc);

        this.transactionButton = new JButton(Constants.SEND_TRANSACTION_BUTTON_ICON);
        this.gbc.gridx = 0; // Cell 0
        this.gbc.gridy = 5; // Cell 5
        this.gbc.ipadx = 0; // Additional Length
        this.gbc.ipady = 0; // Additional Height
        this.gbc.insets = new Insets(0, 0, 0, 0);
        this.transactionButton.setFocusable(false);
        this.transactionButton.setBorder(null);
        this.transactionButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.transactionButton.setContentAreaFilled(false);
        this.transactionButton.setPressedIcon(Constants.SEND_TRANSACTION_BUTTON_ICON_ACTIVE);
        this.transactionButton.setToolTipText("Send Transaction");
        this.transactionButton.addActionListener((e) -> this.sendTransaction());
        this.add(this.transactionButton, this.gbc);
    }

    /**
     * Sends Bitcoin transaction to recipient
     * address equal to amount supplied.
     */
    private void sendTransaction(){
        String amountText = this.amountField.getText().strip();
        String recipientText = this.recipientField.getText().strip();
        String msg;

        if(amountText.length() != 0 && recipientText.length() != 0){
            this.wm.getBitcoin().sendFunds(this.recipientField.getText(), this.amountField.getText());
        }
        else{
            msg = "Ensure amount and recipient fields are not empty.";
            JOptionPane.showMessageDialog(this, msg, "Missing Details", JOptionPane.ERROR_MESSAGE);
        }
    }
}
