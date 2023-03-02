package shop.mtcoding.jobara.apply.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ApplyResp {
    
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CompanyApplyRespDto{
        private Integer userId;
        private Integer boardId;
        private String realName;
        private String title;
        private String jobType;
        private String createdAt;
    }
}
