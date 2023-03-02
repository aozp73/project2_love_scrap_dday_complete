package shop.mtcoding.jobara.util;

import org.junit.jupiter.api.Test;

import shop.mtcoding.jobara.common.util.Sha;

public class ShaTest {

      @Test
      public void Sha_test() throws Exception {
            String password = "12345";
            String encryption = Sha.sha256(password);
            System.out.println(encryption);

            // 1234 : 4b3bed8af7b7612e8c1e25f63ba24496f5b16b2df44efb2db7ce3cb24b7e96f7
            // 12345 : af838d6547c4ca7f4c5247320d0910e4c04da5d21eaccdb831ab31169b9005a1
      }
}
