package shop.mtcoding.jobara.dto.board;

import lombok.Getter;
import lombok.Setter;

public class BoardReq {

    @Getter
    @Setter
    public static class BoardInsertReqDto {
        private String title;
        private String content;
        private String career;
    }
}
