package shop.mtcoding.jobara.company.dto;

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

    @Getter
    @Setter
    public static class CompanyUpdateReqDto {
        private String password;
        private String email;
        private String companyName;
        private String address;
        private String detailAddress;
        private String companyScale;
        private String companyField;
        private String tel;
    }
}
