package shop.mtcoding.jobara.board.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Board {
    private Integer id;
    private Integer companyId;
    private String title;
    private String content;
    private Integer career;
    private String jobType;
    private String education;
    private String favor;
    private Timestamp createdAt;

    public Board(Integer companyId, String title, String content, Integer career) {
        this.companyId = companyId;
        this.title = title;
        this.content = content;
        this.career = career;
    }

    public Board(String title, String content, Integer career, Integer id) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.career = career;
    }
}
