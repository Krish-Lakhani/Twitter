package com.insta.instagram.Services.utility;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncrypter {
    private static final String SECRET_KEY = "hum jaha khade hote he, Password vahi se suru hota he";
    public static String hashPasswordWithStaticSecret(String userPassword) throws NoSuchAlgorithmException {
        String combinedPassword = userPassword + SECRET_KEY;

        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = sha256.digest(combinedPassword.getBytes());

        StringBuilder hexString = new StringBuilder();
        for (byte hashByte : hashBytes) {
            String hex = Integer.toHexString(0xFF & hashByte);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }


    public static boolean verifyPasswordWithStaticSecret(String userPassword, String storedHash) throws NoSuchAlgorithmException {
        String computedHash = hashPasswordWithStaticSecret(userPassword);
        return computedHash.equals(storedHash);
    }
}
