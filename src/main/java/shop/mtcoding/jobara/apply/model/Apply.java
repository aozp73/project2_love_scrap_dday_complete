package shop.mtcoding.jobara.apply.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Apply {
    private Integer id;
    private Integer userId;
    private Integer boardId;
    private Timestamp createdAt;

    public Apply(Integer userId, Integer boardId){
        this.userId = userId;
        this.boardId = boardId;
    }
}
