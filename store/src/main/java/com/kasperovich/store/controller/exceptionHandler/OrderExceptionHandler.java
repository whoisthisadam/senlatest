package com.kasperovich.store.controller.exceptionHandler;


import com.kasperovich.store.excepion.NotPossibleToCreateOrderException;
import com.kasperovich.store.excepion.NotPossibleToDeleteProductException;
import com.kasperovich.store.uitil.UUIDGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class OrderExceptionHandler {

    @ExceptionHandler(value = NotPossibleToCreateOrderException.class)
    public ResponseEntity<Map<String, ErrorContainer>> exception(NotPossibleToCreateOrderException exception){
        return new ResponseEntity<>(Collections.singletonMap("error", ErrorContainer
                .builder()
                .exceptionId(UUIDGenerator.generateUUID())
                .errorCode(2)
                .errorMessage(exception.getMessage())
                .e(exception.getClass().toString())
                .build()), HttpStatus.BAD_REQUEST);
    }
}
