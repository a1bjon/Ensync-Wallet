package utils;

import entities.WalletEntity;

import exceptions.BadNetworkException;

import javax.swing.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;

public final class WalletManager{

    // DATABASE URL
    private final String databaseURL = "jdbc:sqlite:src/main/resources/data/database/Ensync.db";

    // WALLET OBJECTS
    private final Map<String, WalletEntity> wallets = new HashMap<>();

    // LOGGED IN WALLET
    public String loggedInWallet;

    // BITCOIN INSTANCE
    private Bitcoin bitcoin;

    // NETWORK
    private final String network = "main";

    /**
     * Constructs a WalletManager object.
     */
    public WalletManager(){
        // Creating wallets table on instantiation.
        this.createWalletsTable();

        // Loading wallets into memory on instantiation.
        this.loadWallets();
    }

    /**
     * Checks if database exists and if it doesn't, a new
     * database directory and database file is created.
     * Otherwise, the connection object representing a
     * connection to the database is returned.
     * @param url URL of database to connect to.
     * @return Connection object representing connection to database.
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private Connection connect(String url){
        Connection connection = null;
        String msg;

        if(!new File("src/main/resources/data/database/Ensync.db").exists()){
            msg = "> A fatal error occurred [" + new File(url).getName() + "] not found.\n> Fixing Problem...";
            JOptionPane.showMessageDialog(null, msg, "Fatal Error", JOptionPane.ERROR_MESSAGE);

            // Try to create new database and establish connection.
            try{
                new File("src/main/resources/data/database").mkdirs();
                connection = DriverManager.getConnection(url);

                msg = "> Problem has been resolved.\n> WARNING: Old data was not found, create a new wallet.";
                JOptionPane.showMessageDialog(null, msg, "Error Resolved", JOptionPane.INFORMATION_MESSAGE);
            }
            catch(SQLException e){
                msg = "An unknown error occurred.\nRestart Ensync Wallet.";
                JOptionPane.showMessageDialog(null, msg, "Unknown Error", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        }
        else{
            // Establish connection to database otherwise.
            try{
                connection = DriverManager.getConnection(url);
            }
            catch(SQLException e){
                msg = "An unknown error occurred.\nRestart Ensync Wallet.";
                JOptionPane.showMessageDialog(null, msg, "Unknown Error", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        }
        return connection;
    }

    /**
     * Creates a Wallets table in the database
     * with columns for wallet name, password
     * and salt.
     */
    private void createWalletsTable(){
        try(Connection connection = this.connect(this.databaseURL)){
            String sql = "CREATE TABLE IF NOT EXISTS wallets(" +
                    "wallet VARCHAR(20), " +
                    "password VARCHAR(128), " +
                    "salt VARCHAR(32), " +
                    "wallet_bytes LONGBLOB, " +
                    "chain_bytes LONGBLOB)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.execute();
        }
        catch(SQLException e){
            String msg = "An unknown error occurred.\nRestart Ensync Wallet.";
            JOptionPane.showMessageDialog(null, msg, "Unknown Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    /**
     * Selects all rows in the Wallets table
     * and populates this class's wallets HashMap
     * with Wallet objects mapped to these values.
     */
    private void loadWallets(){
        try(Connection connection = this.connect(this.databaseURL)){
            String sql = "SELECT * FROM wallets";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                WalletEntity walletEntity = new WalletEntity();
                walletEntity.setWallet(rs.getString(1));
                walletEntity.setPassword(rs.getString(2));
                walletEntity.setSalt(rs.getString(3));
                walletEntity.setWalletBytes((rs.getBytes(4)));
                walletEntity.setChainBytes((rs.getBytes(5)));

                this.wallets.put(walletEntity.getWallet(), walletEntity);
            }
        }
        catch(SQLException e){
            String msg = "An unknown error occurred.\nRestart Ensync Wallet.";
            JOptionPane.showMessageDialog(null, msg, "Unknown Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    /**
     * Inserts a new wallet and all it's values
     * into the Wallets table if the wallet does
     * not already exist in the database.
     * @param walletEntity Wallet object to be inserted
     * into the database
     * @return true if the wallet and its values
     * were successfully added to the wallets table, false otherwise.
     */
    @SuppressWarnings("ConstantConditions")
    private boolean insertWallet(WalletEntity walletEntity){
        String sql, msg;
        PreparedStatement ps;
        boolean successful = false;

        try(Connection connection = this.connect(this.databaseURL)){
            sql = "SELECT wallet FROM wallets";
            ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            LinkedList<String> walletNameColumnValues = new LinkedList<>();
            while(rs.next()){
                walletNameColumnValues.add(rs.getString(1));
            }

            if(new HashSet<>(walletNameColumnValues).contains(walletEntity.getWallet())){
                msg = "Wallet " + walletEntity.getWallet() + " already exists.";
                JOptionPane.showMessageDialog(null, msg, "Wallet Exists", JOptionPane.ERROR_MESSAGE);
            }
            else{
                sql = "INSERT INTO wallets(wallet, password, salt, wallet_bytes, chain_bytes) VALUES(?, ?, ?, ?, ?)";
                ps = connection.prepareStatement(sql);

                ps.setString(1, walletEntity.getWallet());
                ps.setString(2, walletEntity.getPassword());
                ps.setString(3, walletEntity.getSalt());
                ps.setBytes(4, walletEntity.getWalletBytes());
                ps.setBytes(5, walletEntity.getChainBytes());
                ps.executeUpdate();

                successful = true;
            }
        }
        catch(SQLException e){
            msg = "An unknown error occurred.\nRestart Ensync Wallet.";
            JOptionPane.showMessageDialog(null, msg, "Unknown Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        return successful;
    }

    /**
     * Creates a new wallet.
     * @param wallet Wallet name.
     * @param password Wallet password.
     * @param salt Wallet salt
     * @return true if the wallet was successfully created, false otherwise.
     */
    public boolean createWallet(String wallet, String password, String salt) throws IOException, BadNetworkException, InterruptedException{
        // Creating fresh wallet files.
        this.bitcoin = new Bitcoin(this.network, new File("src/main/resources/data/temp_wallet_data"), "wallet", null);
        // Terminate to unlock spv chain file and read bytes.
        this.bitcoin.terminate();

        boolean successful = false;
        WalletEntity newWalletEntity = new WalletEntity();

        newWalletEntity.setWallet(wallet);
        newWalletEntity.setPassword(password);
        newWalletEntity.setSalt(salt);
        newWalletEntity.setWalletBytes(Files.readAllBytes(new File("src/main/resources/data/temp_wallet_data/wallet.wallet").toPath()));
        newWalletEntity.setChainBytes(Files.readAllBytes(new File("src/main/resources/data/temp_wallet_data/wallet.spvchain").toPath()));

        this.clearWalletData(false, true, 7000);

        if(this.insertWallet(newWalletEntity)){
            this.wallets.put(newWalletEntity.getWallet(), newWalletEntity);
            successful = true;
        }
        return successful;
    }

    /**
     * Checks if the wallet details provided are valid
     * and match a record in the database. Then writes relevant
     * wallet files to disk.
     * @param walletName Wallet name.
     * @param walletPassword Wallet password.
     * @return true if wallet details are valid, false otherwise.
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public boolean login(String walletName, String walletPassword) throws BadNetworkException, IOException {
        boolean granted = false;
        WalletEntity walletEntityObject;
        String hashedPassword;

        if(!this.wallets.isEmpty() && this.wallets.containsKey(walletName)){
            walletEntityObject = this.wallets.get(walletName);
            hashedPassword = Hasher.hashString(walletPassword + walletEntityObject.getSalt());
            if(walletEntityObject.getWallet().equals(walletName) && walletEntityObject.getPassword().equals(hashedPassword)){
                File walletDir = new File("src/main/resources/data/temp_wallet_data");
                if(!walletDir.exists()){
                    walletDir.mkdirs();
                }
                // Writing wallet and spv chain file bytes to disk.
                try(FileOutputStream fos = new FileOutputStream("src/main/resources/data/temp_wallet_data/wallet.wallet")){
                    fos.write(walletEntityObject.getWalletBytes());
                }
                try(FileOutputStream fos = new FileOutputStream("src/main/resources/data/temp_wallet_data/wallet.spvchain")){
                    fos.write(walletEntityObject.getChainBytes());
                }

                this.bitcoin = new Bitcoin(this.network, new File("src/main/resources/data/temp_wallet_data"), "wallet", null);
                granted = true;
            }
        }
        return granted;
    }

    /**
     * Removes wallet from the wallets table in
     * the database.
     * @param walletName Wallet to remove.
     */
    public void deleteWallet(String walletName){
        String msg;

        try(Connection connection = this.connect(this.databaseURL)){
            String sql = "DELETE FROM wallets WHERE wallet = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, walletName);
            ps.executeUpdate();

            this.clearWalletData(true, true, 50);

            msg = walletName + " has been successfully deleted.";
            JOptionPane.showMessageDialog(null, msg, "Wallet Deleted", JOptionPane.INFORMATION_MESSAGE);
        }
        catch(SQLException e){
            msg = "An unknown error occurred.\nRestart Ensync Wallet.";
            JOptionPane.showMessageDialog(null, msg, "Unknown Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    /**
     * Updates the matching wallet's name to the new name provided
     * if the new wallet name is not already assigned to an existing
     * wallet.
     * @param walletName Wallet name to update.
     * @param newWalletName Wallet's new name.
     */
    public void updateWalletName(String walletName, String newWalletName){
        String sql, msg;
        PreparedStatement ps;

        try(Connection connection = this.connect(this.databaseURL)){
            sql = "SELECT wallet FROM wallets";
            ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            LinkedList<String> walletNameColumnValues = new LinkedList<>();
            while(rs.next()){
                walletNameColumnValues.add(rs.getString(1));
            }

            if(new HashSet<>(walletNameColumnValues).contains(newWalletName)){
                msg = newWalletName + " already exists, choose another wallet name.";
                JOptionPane.showMessageDialog(null, msg, "Existing Wallet Name", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                sql = "UPDATE wallets SET (wallet) = (?) WHERE wallet = ?";
                ps = connection.prepareStatement(sql);
                ps.setString(1, newWalletName);
                ps.setString(2, walletName);
                ps.executeUpdate();
                this.loggedInWallet = newWalletName;

                msg = "Wallet name successfully changed to " + newWalletName + ".";
                JOptionPane.showMessageDialog(null, msg, "Wallet Name Changed", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch(SQLException e){
            msg = "An unknown error occurred.\nRestart Ensync Wallet.";
            JOptionPane.showMessageDialog(null, msg, "Unknown Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    /**
     * Deletes all files in wallet data directory and qrcode
     * file associated with current wallet.
     * @param terminate Whether Bitcoin instance should be terminated.
     * @param gc Whether garbage collector should be called.
     */
    @SuppressWarnings({"ConstantConditions", "ResultOfMethodCallIgnored"})
    public void clearWalletData(boolean terminate, boolean gc, long millis){
        if(terminate){
            this.bitcoin.terminate();
        }

        // Calling garbage collector to release
        // spv chain file resources preventing file deletion.
        if(gc){
            System.gc();
            System.runFinalization();
        }

        // Sleep thread for wallet file to save
        // to ensure wallet file can be deleted.
        try{
            Thread.sleep(millis);
        }
        catch(InterruptedException ignored){}

        // Delete all files in wallet_data
        // after wallet generation.
        File[] walletDataFiles = new File("src/main/resources/data/temp_wallet_data").listFiles();
        for(int i = 0; i < walletDataFiles.length; i++){
            walletDataFiles[i].delete();
        }
    }

    /**
     * Checks if password for the view recovery
     * frame is valid for the logged in wallet.
     * @param walletName Logged in wallet.
     * @param password Password supplied.
     * @return True if password matches currently logged
     * in wallet, false otherwise.
     */
    public boolean viewPhrase(String walletName, String password){
        WalletEntity walletEntityObject = this.wallets.get(walletName);
        String testPass = Hasher.hashString(password + walletEntityObject.getSalt());
        return testPass.equals(walletEntityObject.getPassword());
    }

    /**
     * Returns this class's Bitcoin instance.
     * @return This class's Bitcoin instance.
     */
    public Bitcoin getBitcoin(){
        return this.bitcoin;
    }
}
