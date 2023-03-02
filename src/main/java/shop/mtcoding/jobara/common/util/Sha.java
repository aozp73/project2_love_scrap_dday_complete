package shop.mtcoding.jobara.common.util;

import java.security.MessageDigest;

public class Sha {

      public static String sha256(String password) throws Exception {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String salt = password + "salt";
            md.update(salt.getBytes("UTF-8"));
            byte[] HashByte = md.digest();
            StringBuffer hash = new StringBuffer();

            for (byte b : HashByte) {
                  String hexString = String.format("%02x", b);
                  hash.append(hexString);
            }

            String passwordHash = hash.toString();
            return passwordHash;
      }

}