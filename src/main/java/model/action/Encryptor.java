package model.action;

import javax.security.auth.login.LoginException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryptor {
    public String getHashPassword(String password) throws LoginException, NoSuchAlgorithmException {
        MessageDigest digestMD5 = MessageDigest.getInstance("MD5");
        byte[] bytes = digestMD5.digest(password.getBytes());
        StringBuilder stringBuilder = new StringBuilder();
        for(byte b : bytes){
            stringBuilder.append(String.format("%02X ", b));
        }
        return stringBuilder.toString();
    }
}
