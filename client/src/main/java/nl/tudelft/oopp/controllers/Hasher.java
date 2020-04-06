package nl.tudelft.oopp.controllers;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.commons.codec.binary.Hex;


public class Hasher {

    /**
     * The method that hashes the given password using PBKDF2 hasing algorithm.
     * @author Kanish Dwivedi
     * @param password - The password that needs to be hashed (as an array of char)
     * @return String - The hashed password represented as a string
     */
    public static String hashPassword(String password) {
        String salt = "1234";
        final int iterations = 10000;
        final int keyLength = 250;

        final char[] passwordChars = password.toCharArray();
        final byte[] saltBytes = salt.getBytes();

        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            PBEKeySpec spec = new PBEKeySpec(passwordChars, saltBytes, iterations, keyLength);
            SecretKey key = skf.generateSecret(spec);
            byte[] hashedBytes = key.getEncoded();

            return Hex.encodeHexString(hashedBytes);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }



}
