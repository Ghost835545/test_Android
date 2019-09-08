package com.example.ivan.loginapp.util;
import java.security.MessageDigest;

import android.util.Log;

public class Security {
    public static String encryptPass(String password) {
        try {
            if (java.security.Security.getProvider("JStribog") == null) {
                java.security.Security.addProvider(new com.example.ivan.loginapp.util.striBog.StribogProvider());
            }
            MessageDigest md = MessageDigest.getInstance("Stribog256");
            byte[] digest = md.digest(password.getBytes());
            return printHex(digest);
        } catch (Exception e) {
            Log.d("encryptPass", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    private static String printHex(byte[] digest) {
        String hex = "";
        for (byte b : digest) {
            int iv = (int) b & 0xFF;
            if (iv < 0x10) {
                hex += '0';
            }
            hex += Integer.toHexString(iv).toUpperCase();
        }
        return hex;
    }
    /*public static String encryptPass(String password) {
        try {
            byte[] keyStart = "1dj3fcdbgh4jf".getBytes(StandardCharsets.UTF_8);
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            sr.setSeed(keyStart);
            kgen.init(128, sr);
            SecretKey skey = kgen.generateKey();
            byte[] key = skey.getEncoded();
            SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = cipher.doFinal(password.getBytes(StandardCharsets.UTF_8));
            String sumPass = Base64.encodeToString(encrypted, Base64.NO_PADDING) +
                    Base64.encodeToString(key, Base64.NO_PADDING);
            String[] string = sumPass.split("\n");
            String str = "";
            for (String s : string) {
                if (!s.isEmpty()) str += s;
            }
            return str;
        } catch (Exception e) {
            Log.d("encryptPass", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    public static String decryptPass(String encrypted)   {
        try {
            String key = encrypted.substring(encrypted.length() - 22);
            String password = encrypted.substring(0, encrypted.length() - 22);
            SecretKeySpec skeySpec = new SecretKeySpec(Base64.decode(key, Base64.NO_PADDING), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] decrypted = cipher.doFinal(Base64.decode(password, Base64.NO_PADDING));
            return new String(decrypted, StandardCharsets.UTF_8);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }*/

}
