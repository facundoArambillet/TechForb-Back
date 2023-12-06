package com.TF.TechForb.error;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class TechForbException extends RuntimeException {
    private HttpStatus httpStatus;
    private String error;

    public TechForbException(String message, String error, HttpStatus httpStatus) {
        super(message);
        this.error = error;
        this.httpStatus = httpStatus;
    }

}
