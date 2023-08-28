package com.caleumtatsu2010.utility.security.encrypt.password;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class EncodeHasher {

    public static String getSecurePassword(String password, byte[] salt, String algorithm) {

        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(salt);
            byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;//hashed Hasher with salt
    }

    public static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {

        // same salt should be passed
        byte[] salt = getSalt();
        System.out.println("Salt ->"+ Base64.getEncoder().encodeToString(salt));
        String password1 = getSecurePassword("1234", salt, "SHA-512");
        String password2 = getSecurePassword("1234", salt, "SHA-512");
        System.out.println(" Password 1 -> " + password1);
        System.out.println(" Password 2 -> " + password2);
        if (password1.equals(password2)) {
            System.out.println("passwords are equal");
        }

    }


}
