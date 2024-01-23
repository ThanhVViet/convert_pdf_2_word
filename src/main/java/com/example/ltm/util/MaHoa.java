package com.example.ltm.util;

import java.security.MessageDigest;
import java.util.Base64;


public class MaHoa {

    public static String toSHA1(String str) {
        String salt = "asjrlkmcoewj@tjle;oxqskjhdjksjf1jurVn";
        String result = null;

        str = str + salt;
        try {
            byte[] dataBytes = str.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-1");
//            result = Base64.encodeBase64String(md.digest(dataBytes));
            byte[] hashBytes = md.digest(dataBytes);
            result = Base64.getEncoder().encodeToString(hashBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
