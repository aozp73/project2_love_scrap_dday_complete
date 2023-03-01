package shop.mtcoding.jobara.resume.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Resume {
    private Integer id;
    private Integer userId;
    private String title;
    private String content;

    public Resume(Integer userId, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;
    }
}
