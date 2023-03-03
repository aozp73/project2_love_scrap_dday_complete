package shop.mtcoding.jobara.common.util;

import java.security.MessageDigest;

import org.springframework.http.HttpStatus;

import shop.mtcoding.jobara.common.ex.CustomException;

public class Sha {
      public static String sha256(String password) {
            String passwordHash = password;
            try {
                  MessageDigest md = MessageDigest.getInstance("SHA-256");
                  String salt = password + "salt";
                  md.update(salt.getBytes("UTF-8"));
                  byte[] HashByte = md.digest();
                  StringBuffer hash = new StringBuffer();
                  for (byte b : HashByte) {
                        String hexString = String.format("%02x", b);
                        hash.append(hexString);
                  }
                  passwordHash = hash.toString();
            } catch (Exception e) {
                  throw new CustomException("암호화에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return passwordHash;
      }

}