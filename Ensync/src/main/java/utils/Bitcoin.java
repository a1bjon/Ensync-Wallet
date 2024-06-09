package utils;

import exceptions.BadNetworkException;

import javax.swing.*;

import java.io.File;
import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;

import org.bitcoinj.core.*;
import org.bitcoinj.kits.WalletAppKit;
import org.bitcoinj.wallet.DeterministicSeed;
import org.bitcoinj.wallet.SendRequest;
import org.bitcoinj.wallet.Wallet;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.params.TestNet3Params;

public final class Bitcoin{

    // BITCOIN NETWORK
    private final NetworkParameters network;

    // WALLET APP KIT
    private WalletAppKit kit;

    /**
     * Constructs a Bitcoin object.
     * @param network Bitcoin network to use.
     * @param dir Directory to wallet and spvchain files.
     * @param prefix File names.
     * @param password Password to encrypt wallet file with.
     * @throws BadNetworkException If "main" or "test" is not passed as an argument.
     */
    public Bitcoin(String network, File dir, String prefix, String password) throws BadNetworkException {
        switch(network){
            case "main":
                this.network = MainNetParams.get();
                break;

            case "test":
                this.network = TestNet3Params.get();
                break;

            default:
                throw new BadNetworkException("Bad network [" + network + "]. Must be \"main\" or \"test\"");
        }

        this.setup(dir, prefix, password);
    }

    /**
     * Sets the wallet up by creating a wallet and downloading and
     * syncing with the blockchain.
     * @param dir Directory where the wallet and spvchain file wil be written to.
     * @param prefix Name for wallet and spvchain files.
     * @param password Password which will be used to encrypt wallet file.
     */
    private void setup(File dir, String prefix, String password){
        this.kit = new WalletAppKit(this.network, dir, prefix){
            @Override
            protected void onSetupCompleted(){
                System.out.println("wallet created");
            }
        };

        this.kit.startAsync();
        this.kit.awaitRunning();
    }

    /**
     * Terminates currently active wallet app kit.
     */
    public void terminate(){
        this.kit.stopAsync();
        this.kit.awaitTerminated();
    }

    /**
     * Gets the current wallet's Bitcoin address.
     * @return The current wallet's Bitcoin address.
     */
    public String getAddress(){
        return this.kit.wallet().currentReceiveAddress().toString();
    }

    /**
     * Gets the 12 word mnemonic phrase associated with the
     * current wallet used to restore wallet.
     * @return 12 word mnemonic phrase.
     */
    @SuppressWarnings("ConstantConditions")
    public String getMnemonicPhrase(){
        DeterministicSeed ds = this.kit.wallet().getKeyChainSeed();
        return String.join(" - ", ds.getMnemonicCode());
    }

    /**
     * Gets current Bitcoin balance of wallet by converting
     * Satoshi's to Bitcoin equivalent.
     * @return Current wallet Bitcoin balance.
     */
    public String getBalance(){
        return new BigDecimal(Double.toString(Double.parseDouble(this.kit.wallet().getBalance().toString()) / 100000000)).toPlainString();
    }

    /**
     * Gets total Bitcoin received by this wallet by converting
     * total number of Satoshi's received to Bitcoin equivalent.
     * @return Total Bitcoin received.
     */
    public String getTotalReceived(){
        return new BigDecimal(Double.toString(Double.parseDouble(this.kit.wallet().getTotalReceived().toString()) / 100000000)).toPlainString();
    }

    /**
     * Creates a transaction request equal to amount requested.
     * @param recipient Recipient to send funds to.
     * @param amount Amount of Bitcoin to send.
     */
    public void sendFunds(String recipient, String amount){
        Address addr = Address.fromString(this.network, recipient);
        Coin val = Coin.parseCoin(amount);
        SendRequest request = SendRequest.to(addr, val);
        String msg;

        try{
            Wallet.SendResult result = this.kit.wallet().sendCoins(request);
            Transaction transaction = result.broadcastComplete.get();
            msg = amount + " BTC has been sent to [" + recipient + "].\nTransaction ID: " + transaction.getTxId() + ".";
            JOptionPane.showMessageDialog(null, msg, "Transaction Completed", JOptionPane.ERROR_MESSAGE);
        }
        catch(AddressFormatException e){
            msg = "Recipient address is not a valid mainnet address." +
                    "\nEnsure the recipient address is entered correctly and is not a testnet address.";
            JOptionPane.showMessageDialog(null, msg, "Invalid Address", JOptionPane.ERROR_MESSAGE);
        }
        catch(InsufficientMoneyException  e){
            msg = "Transaction failed due to insufficient funds.";
            JOptionPane.showMessageDialog(null, msg, "Insufficient Balance", JOptionPane.ERROR_MESSAGE);
        }
        catch(InterruptedException | ExecutionException e){
            msg = "Transaction failed due to unknown reason." + "\nTry again later.";
            JOptionPane.showMessageDialog(null, msg, "Transaction Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
}
