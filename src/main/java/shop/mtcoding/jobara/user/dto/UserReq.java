package shop.mtcoding.jobara.user.dto;

import lombok.Getter;
import lombok.Setter;

public class UserReq {

      @Getter
      @Setter
      public static class UserLoginReqDto {
            private String username;
            private String password;
      }

      @Getter
      @Setter
      public static class UserJoinReqDto {
            private String username;
            private String password;
            private String email;
      }

      @Getter
      @Setter
      public static class UserUpdateReqDto {
            private String username;
            private String password;
            private String email;
            private String address;
            private String detailAddress;
            private String tel;
            private Integer career;
      }

      @Getter
      @Setter
      public static class UserResumeFormReqDto {
            private String resumeTitle;
            private String resumeContent;
      }

}
