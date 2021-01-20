package com.innopolis.eventgo.restcontroller;

import com.innopolis.eventgo.db.entity.ErrorEntity;
import com.innopolis.eventgo.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandleErrorController {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorEntity> handleNotFoundPost(NotFoundException e) {
        ErrorEntity entity = new ErrorEntity(404, e.getMessage());
        return new ResponseEntity<>(entity, HttpStatus.NOT_FOUND);
    }
}
