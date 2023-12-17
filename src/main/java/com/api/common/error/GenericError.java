package com.api.common.error;


import com.api.error.UserCreationException;
import com.api.error.UserUpdateException;
import com.api.model.GenericExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GenericError extends ResponseEntityExceptionHandler {
    //buraya eklicez exceptionları
    @ExceptionHandler({
            UserCreationException.class,
            UserUpdateException.class
    })
    public ResponseEntity<Object> handleCustomException(Exception e) {
        GenericExceptionResponse error = new GenericExceptionResponse(e.getMessage(), new Date());
        return new ResponseEntity<>(error, HttpStatus.OK);
    }

}
