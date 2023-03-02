package shop.mtcoding.jobara.apply.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.jobara.common.util.DateParse;

public class ApplyResp {

    @Getter
    @Setter
    public static class CompanyApplyRespDto {
        private Integer userId;
        private Integer boardId;
        private String realName;
        private String title;
        private Integer state;
        private Timestamp createdAt;

        public String getCreatedAtToString() {
            return DateParse.format(createdAt);
        }

        public String getStateToString() {
            switch (state) {
                case 1:
                    return "합격";
                case -1:
                    return "불합격";
                default:
                    return "검토중";
            }
        }
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
        private Integer state;
        private Timestamp createdAt;

        public String getCreatedAtToString() {
            return DateParse.format(createdAt);
        }

        public String getStateToString() {
            switch (state) {
                case 1:
                    return "합격";
                case -1:
                    return "불합격";
                default:
                    return "검토중";
            }
        }
    }
}
