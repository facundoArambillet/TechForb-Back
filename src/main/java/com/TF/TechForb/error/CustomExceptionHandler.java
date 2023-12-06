package com.TF.TechForb.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = TechForbException.class)
    public ResponseEntity handleResponseException(TechForbException e) {
        ApiException apiException = new ApiException(
                e.getMessage(),
                e.getError(),
                e.getHttpStatus()
        );
        return new ResponseEntity<>(apiException,e.getHttpStatus());
    }

}
