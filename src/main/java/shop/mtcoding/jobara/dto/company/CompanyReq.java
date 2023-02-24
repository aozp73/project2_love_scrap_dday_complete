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

    @Getter
    @Setter
    public static class CompanyJoinReqDto {
        private String username;
        private String password;
        private String email;
        private Long companyNumb;
        private String companyName;
        private String address;
        private String detailAddress;
    }
}
