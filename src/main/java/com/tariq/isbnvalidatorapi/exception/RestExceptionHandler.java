package com.tariq.isbnvalidatorapi.exception;

import com.tariq.isbnvalidatorapi.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(value = { InvalidIsbnFormatException.class })
    public ResponseEntity handleNotFoundException(InvalidIsbnFormatException ex) {
        return new ResponseEntity(new ErrorResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
