package shop.mtcoding.jobara.apply.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.jobara.common.util.DateParse;

@Getter
@Setter
@NoArgsConstructor
public class Apply {
    private Integer id;
    private Integer userId;
    private Integer boardId;
    private Integer state;
    private Timestamp createdAt;

    public Apply(Integer boardId, Integer userId) {
        this.userId = userId;
        this.boardId = boardId;
    }

    public String getCreatedAtToString() {
        return DateParse.format(createdAt);
    }
}
