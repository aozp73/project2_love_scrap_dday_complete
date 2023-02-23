package shop.mtcoding.jobara.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String address;
    private String detailAddress;
    private String tel;
    private String realName;
    private Integer career;
    private String resumeTitle;
    private String resumeContent;
    private Timestamp createdAt;
}
