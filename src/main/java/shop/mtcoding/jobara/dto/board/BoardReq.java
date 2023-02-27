package shop.mtcoding.jobara.dto.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class BoardReq {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BoardInsertReqDto {
        private String title;
        private String content;
        private String career;
    }

    @Getter
    @Setter
    public static class BoardUpdateReqDto {
        private Integer id;
        private String title;
        private String content;
        private String careerString;
        private Integer career;
    }
}
