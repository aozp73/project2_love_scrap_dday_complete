package shop.mtcoding.jobara.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Company {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String address;
    private String detailAddress;
    private String tel;
    private String companyName;
    private String companyScale;
    private Long companyNumb;
    private String companyField;
    private Timestamp createdAt;

    public Company(String username, String password){
        this.username = username;
        this.password = password;
    }
    
    public Company(String username, String password, String email, String address, String detailAddress, 
            Long companyNumb, String companyName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.detailAddress = detailAddress;
        this.companyNumb = companyNumb;
        this.companyName = companyName;
    }
}
