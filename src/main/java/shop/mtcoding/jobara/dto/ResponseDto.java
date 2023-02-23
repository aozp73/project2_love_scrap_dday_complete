package shop.mtcoding.jobara.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseDto<T> {
    private Integer code;
    private String msg;
    private T data;
}
