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
    private Integer java;
    private Integer cLang;
    private Integer python;
    private Integer php;
    private Integer jsc;
    private Integer ruby;
    private Integer assemblyLang;
    private Integer sqlLang;
    private Timestamp createdAt;
}
