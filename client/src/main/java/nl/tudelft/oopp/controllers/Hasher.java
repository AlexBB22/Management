package nl.tudelft.oopp.controllers;


import org.apache.commons.codec.binary.Hex;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class Hasher {

    public static void main(String args[]) {
        String password = "password";
        //just a test
        System.out.println(Hasher.hashPassword(password));
    }

    /**
     * The method that hashes the given password using PBKDF2 hasing algorithm
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
