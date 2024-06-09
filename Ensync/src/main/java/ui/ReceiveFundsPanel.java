package ui;

import exceptions.NullAddressArgumentException;
import exceptions.InvalidCharsetArgumentException;

import utils.Constants;
import utils.WalletManager;

import javax.swing.*;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class ReceiveFundsPanel extends JPanel {

    // COMPONENT CONSTRAINTS INSTANCE
    private final GridBagConstraints gbc = new GridBagConstraints();

    // WALLET MANAGER
    private final WalletManager wm;

    // WALLET ADDRESS
    private final String walletAddress;

    // LOGO LABEL
    private final JLabel logoLabel;

    // QRCODE ADDRESS LABEL
    private final JLabel QRAddressLabel;

    // ADDRESS LABEL
    private final JLabel yourAddressLabel;

    // ADDRESS LABEL
    private final JLabel addressLabel;

    // ADDRESS LABEL
    private final JButton copyAddressButton;

    /**
     * Constructs a ReceiveFundsPanel object.
     * @param wm Wallet manager instance.
     */
    public ReceiveFundsPanel(WalletManager wm){
        this.setLayout(new GridBagLayout());
        this.setBackground(Constants.GREY12);

        // WALLET MANAGER INSTANCE
        this.wm = wm;

        // ASSIGNING CURRENT WALLET ADDRESS VALUE
        this.walletAddress = this.wm.getBitcoin().getAddress();

        this.logoLabel = new JLabel(Constants.ENSYNC_LOGO);
        this.gbc.gridx = 0; // Cell 0
        this.gbc.gridy = 0; // Cell 0
        this.gbc.ipadx = 0; // Additional Length
        this.gbc.ipady = 0; // Additional Height
        this.gbc.insets = new Insets(0, 0, 70, 0);
        this.add(this.logoLabel, this.gbc);

        this.QRAddressLabel = new JLabel(this.generateQRCode(this.walletAddress, StandardCharsets.UTF_8.toString()));
        this.gbc.gridx = 0; // Cell 0
        this.gbc.gridy = 1; // Cell 1
        this.gbc.ipadx = 0; // Additional Length
        this.gbc.ipady = 0; // Additional Height
        this.gbc.insets = new Insets(0, 0, 20, 0);
        this.QRAddressLabel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Constants.ENSYNC_BLUE));
        this.add(this.QRAddressLabel, this.gbc);

        this.yourAddressLabel = new JLabel("Your BTC Address");
        this.gbc.gridx = 0; // Cell 0
        this.gbc.gridy = 2; // Cell 2
        this.gbc.ipadx = 0; // Additional Length
        this.gbc.ipady = 0; // Additional Height
        this.gbc.insets = new Insets(0, 0, 10, 0);
        this.yourAddressLabel.setForeground(Constants.ENSYNC_BLUE);
        this.yourAddressLabel.setFont(new Font("consolas", Font.BOLD, 15));
        this.add(this.yourAddressLabel, this.gbc);

        this.addressLabel = new JLabel(this.walletAddress);
        this.gbc.gridx = 0; // Cell 0
        this.gbc.gridy = 3; // Cell 3
        this.gbc.ipadx = 0; // Additional Length
        this.gbc.ipady = 0; // Additional Height
        this.gbc.insets = new Insets(0, 0, 110, 0);
        this.addressLabel.setForeground(Constants.ENSYNC_BLUE);
        this.addressLabel.setFont(new Font("consolas", Font.BOLD, 30));
        this.add(this.addressLabel, this.gbc);

        this.copyAddressButton = new JButton(Constants.COPY_ADDRESS_BUTTON_ICON);
        this.gbc.gridx = 0; // Cell 0
        this.gbc.gridy = 3; // Cell 3
        this.gbc.ipadx = 0; // Additional Length
        this.gbc.ipady = 0; // Additional Height
        this.gbc.insets = new Insets(0, 0, 0, 0);
        this.copyAddressButton.setFocusable(false);
        this.copyAddressButton.setBorder(null);
        this.copyAddressButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.copyAddressButton.setContentAreaFilled(false);
        this.copyAddressButton.setPressedIcon(Constants.COPY_ADDRESS_BUTTON_ICON_ACTIVE);
        this.copyAddressButton.setToolTipText("Copy Address");
        this.copyAddressButton.addActionListener((e) -> this.copyAddress(this.walletAddress));
        this.add(this.copyAddressButton, this.gbc);
    }

    /**
     * Generates a QRCode from the string and encodes it using the charset supplied.
     * @param s String to generate QRCode from.
     * @param charset Encoding to use.
     * @return ImageIcon object using generated QRCode.
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private ImageIcon generateQRCode(String s, String charset){
        String[] validUTF = {"UTF-8", "UTF-16", "UTF-32"};

        if(s == null){
            throw new NullAddressArgumentException("Supplied address argument is null.");
        }
        else if(charset == null || !Arrays.asList(validUTF).contains(charset)){
            throw new InvalidCharsetArgumentException("Supplied charset argument is null or not valid UTF encoding");
        }

        try{
            BitMatrix matrix = new MultiFormatWriter().encode(new String(s.getBytes(charset), charset), BarcodeFormat.QR_CODE, 250, 250);
            MatrixToImageWriter.writeToPath(matrix, "png", new File("src/main/resources/data/temp_wallet_data/qrcode.png").toPath());
        }
        catch(WriterException | IOException e){
            return new ImageIcon("src/main/resources/images/other/qrcode_error_icon.png");
        }
        return new ImageIcon("src/main/resources/data/temp_wallet_data/qrcode.png");
    }

    /**
     * Copies wallet address to system clipboard.
     * @param addr Bitcoin address to copy.
     */
    private void copyAddress(String addr){
        StringSelection selection = new StringSelection(addr);
        Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
        c.setContents(selection, null);
    }
}
