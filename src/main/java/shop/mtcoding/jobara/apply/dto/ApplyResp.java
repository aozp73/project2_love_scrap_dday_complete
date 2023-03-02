package shop.mtcoding.jobara.apply.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ApplyResp {

    @Getter
    @Setter
    public static class CompanyApplyRespDto {
        private Integer userId;
        private Integer boardId;
        private String realName;
        private String title;
        private Integer jobType;
        private Timestamp createdAt;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EmployeeApplyRespDto {
        private Integer userId;
        private Integer boardId;
        private String resumeTitle;
        private String boardTitle;
        private Integer jobType;
        private Timestamp createdAt;
    }
}
