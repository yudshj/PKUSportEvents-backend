package org.pkuse2020grp4.pkusporteventsbackend.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class Salt {
    public static String generateSalt(int op){
        if(op != 0)
            return "";
        Integer count = 10;
        Random r = new Random();
        StringBuilder salt = new StringBuilder("user");
        for(int i = 0; i < count; i ++){
            salt.append(Integer.toHexString(r.nextInt() % 16));
        }
        return salt.toString();
    }
    public static String salty(String password, String salt, int op){
        if(op == 0)
            return md5(password + salt);
        else
            return bcrypt(password);
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
    private static String bcrypt(String rawPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        return encodedPassword;
    }
}
