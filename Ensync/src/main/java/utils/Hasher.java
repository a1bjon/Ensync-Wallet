package utils;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.Collections;

public final class Hasher {

    // PRIVATE ACCESS CONSTRUCTOR
    private Hasher(){}

    /**
     * Generates a 32 character cryptographically secure salt.
     * @return Cryptographically secure salt.
     */
    public static String generateSalt() {
        SecureRandom sr = new SecureRandom();
        LinkedList<String> l = new LinkedList<>();
        String alpha = "zvrutidylxjfgwcphnoqkmeasb";

        // Add 16 random upper and lower case chars
        // from alpha string.
        for(int i = 0; i < 16; i++){
            if(i % 2 == 0){
                l.add(String.valueOf(alpha.charAt(sr.nextInt(alpha.length()))).toLowerCase());
                continue;
            }
            l.add(String.valueOf(alpha.charAt(sr.nextInt(alpha.length()))).toUpperCase());
        }

        // Add 16 random ints (0 - 9) with 
        // unsigned 1 bit right shift.
        for(int i = 0; i < 16; i++){
            l.add(Integer.toString(sr.nextInt(10) >>> 1));
        }

        // Shuffle salt array 10 times.
        for(int i = 0; i < 10; i++){
            Collections.shuffle(l, sr);
        }

        // Convert to string.
        return String.join("", l);
    }

    /**
     * Hashes the text using the SHA-512 if JVM is 
     * 64-bit or SHA-256 if JVM is 32-bit/other.
     * @param s String to be hashed
     * @return Hashed string using appropriate algorithm.
     */
    public static String hashString(String s){
        if(System.getProperty("os.arch").contains("64")){
            return Hashing.sha512().hashString(s, StandardCharsets.UTF_8).toString();
        }
        return Hashing.sha256().hashString(s, StandardCharsets.UTF_8).toString();
    }
}
