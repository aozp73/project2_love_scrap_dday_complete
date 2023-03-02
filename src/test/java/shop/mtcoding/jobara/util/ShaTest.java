package shop.mtcoding.jobara.util;

import org.junit.jupiter.api.Test;

import shop.mtcoding.jobara.common.util.Sha;

public class ShaTest {

      @Test
      public void Sha_test() throws Exception {
            String password = "1234";
            String encryption = Sha.sha256(password);
            System.out.println(encryption);
      }
}
