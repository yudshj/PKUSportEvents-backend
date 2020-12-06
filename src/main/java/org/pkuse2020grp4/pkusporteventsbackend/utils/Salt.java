package org.pkuse2020grp4.pkusporteventsbackend.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class Salt {
    public static String generateSalt(){
        Integer count = 10;
        Random r = new Random();
        StringBuilder salt = new StringBuilder("user");
        for(int i = 0; i < count; i ++){
            salt.append(Integer.toHexString(r.nextInt() % 16));
        }
        return salt.toString();
    }
    public static String salty(String password, String salt){
        String tempPassword = password + salt;
        return md5(tempPassword);
    }
    private static String md5(String data){
        StringBuilder sb = new StringBuilder();
        try{
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] md5 = md.digest(data.getBytes(StandardCharsets.UTF_8));
            for(byte b : md5){
                sb.append(Integer.toHexString(b & 0xff));
            }
        } catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return sb.toString();
    }
}
