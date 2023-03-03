package shop.mtcoding.jobara.util;

import org.junit.jupiter.api.Test;

import shop.mtcoding.jobara.common.util.Hash;

public class ShaTest {

      @Test
      public void Sha_test() {
            String password = "12345";
            String encryption = null;
            try {
                  encryption = Hash.encode(password);
            } catch (Exception e) {
                  System.out.println("테스트 실패");
            }
            System.out.println(encryption);

            // 1234 : 03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4
            // 12345 : 5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5
      }
}
