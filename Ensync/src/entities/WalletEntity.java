package entities;

import java.util.Arrays;

public final class WalletEntity {

    // WALLET NAME
    private String wallet;

    // WALLET PASSWORD
    private String password;

    // PASSWORD SALT
    private String salt;

    // WALLET FILE BYTES
    private byte[] walletBytes;

    // SPV CHAIN FILE BYTES
    private byte[] chainBytes;

    // SETTERS
    /**
     * Sets value of this object's 'wallet' field.
     * @param wallet value of this object's 'wallet' field.
     */
    public void setWallet(String wallet){
        this.wallet = wallet;
    }

    /**
     * Sets value of this object's 'password' field.
     * @param password value of this object's 'password' field.
     */
    public void setPassword(String password){
        this.password = password;
    }

    /**
     * Sets value of this object's 'salt' field.
     * @param salt value of this object's 'salt' field.
     */
    public void setSalt(String salt){
        this.salt = salt;
    }

    /**
     * Sets value of this object's 'walletBytes' field.
     * @param walletBytes value of this object's 'walletBytes' field.
     */
    public void setWalletBytes(byte[] walletBytes){
        this.walletBytes = walletBytes;
    }

    /**
     * Sets value of this object's 'chainBytes' field.
     * @param chainBytes value of this object's 'chainBytes' field.
     */
    public void setChainBytes(byte[] chainBytes){
        this.chainBytes = chainBytes;
    }

    // GETTERS
    /**
     * Gets value of this object's 'wallet' field.
     * @return value of this object's 'wallet' field.
     */
    public String getWallet(){
        return this.wallet;
    }

    /**
     * Gets value of this object's 'password' field.
     * @return value of this object's 'password' field.
     */
    public String getPassword(){
        return this.password;
    }

    /**
     * Gets value of this object's 'salt' field.
     * @return value of this object's 'salt' field.
     */
    public String getSalt(){
        return this.salt;
    }

    /**
     * Gets value of this object's 'walletBytes' field.
     * @return value of this object's 'walletBytes' field.
     */
    public byte[] getWalletBytes(){
        return this.walletBytes;
    }

    /**
     * Gets value of this object's 'chainBytes' field.
     * @return value of this object's 'chainBytes' field.
     */
    public byte[] getChainBytes(){
        return this.chainBytes;
    }

    /**
     * Returns human-readable string of a Wallet Entity object.
     * @return String representing a Wallet Entity object.
     */
    @Override
    public String toString(){
        return "WalletEntity{" +
                "wallet = '" + this.wallet + "', " +
                "password = '" + this.password + "', " +
                "salt = '" + this.salt + "', " +
                "walletBytes = '" + Arrays.toString(this.walletBytes) + "', " +
                "chainBytes = '" + Arrays.toString(this.chainBytes) + "'}";
    }
}
