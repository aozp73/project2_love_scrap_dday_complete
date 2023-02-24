package shop.mtcoding.jobara.dto.board;

import lombok.Getter;
import lombok.Setter;

public class BoardResp {
    @Getter
    @Setter
    public static class BoardMainRespDto {
        private Integer id;
        private String title;
        private String companyName;
        private Integer companyId;
    }

    @Getter
    @Setter
    public static class BoardDetailRespDto {
        private Integer id;
        private String title;
        private String content;
        private Integer career;
        private Integer companyId;
        private String companyName;
        private String companyScale;
        private String companyField;
    }

}
