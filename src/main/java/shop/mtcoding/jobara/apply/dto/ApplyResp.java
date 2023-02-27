package shop.mtcoding.jobara.apply.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

public class ApplyResp {
    
    @Getter
    @Setter
    public static class ListRespDto {
        private Integer id;
        private Integer userId;
        private Integer boardId;
        private String realName;
        private String title;
        private String jobType;
        private Timestamp createdAt;
    }
}
