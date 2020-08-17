package com.op.gg.ogj.config.domain.response;

import lombok.Data;
import org.springframework.http.HttpStatus;
import java.time.LocalDate;

@Data
public class ResponseDto {

    private static final long serialVersionUID = 1L;

    private int code;
    private String message;
    private LocalDate timestamp;
    private Object data;

    public ResponseDto() {
        this.code = HttpStatus.OK.value();
        this.message = HttpStatus.OK.getReasonPhrase();
        this.timestamp = LocalDate.now();
    }

    public ResponseDto(HttpStatus httpStatus) {
        this.code = httpStatus.value();
        this.message = httpStatus.getReasonPhrase();
        this.timestamp = LocalDate.now();
    }

    public ResponseDto(HttpStatus httpStatus, Object obj) {
        this.code = httpStatus.value();
        this.message = httpStatus.getReasonPhrase();
        this.timestamp = LocalDate.now();
        this.data = obj;
    }

    public static ResponseDto of(Object obj) {
        ResponseDto rd =  new ResponseDto();
        rd.setData(obj);
        return rd;
    }

}
