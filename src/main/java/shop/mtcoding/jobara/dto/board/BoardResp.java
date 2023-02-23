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

}
