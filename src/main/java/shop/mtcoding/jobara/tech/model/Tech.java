package shop.mtcoding.jobara.tech.model;

import java.security.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tech {
    private Integer id;
    private Integer userId;
    private Integer boardId;
    private String java;
    private String cLang;
    private String python;
    private String php;
    private String jsc;
    private String ruby;
    private String assemblyLang;
    private String sqlLang;
    private Timestamp createdAt;
}
