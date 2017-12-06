package com.tendimensions;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashes {

    public static String getMD5(String toHash) {
        return hash(toHash, "MD5");
    }

    public static String getSHA1(String toHash) {
        return hash(toHash, "SHA-1");
    }

    public static String getSHA256(String toHash) {
        return hash(toHash, "SHA-256");
    }

    public static String getSHA512(String toHash) {
        return hash(toHash, "SHA-512");
    }

    private static String hash(String toHash, String algorithm) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            byte[] messageDigest = md.digest(toHash.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }
}
