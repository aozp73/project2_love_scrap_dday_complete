package shop.mtcoding.jobara.dto.company;

import lombok.Getter;
import lombok.Setter;

public class CompanyReq {
    
    @Getter
    @Setter
    public static class CompanyLoginReqDto{
        private String username;
        private String password;
    }
}
