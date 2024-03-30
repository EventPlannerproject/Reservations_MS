package com.eventplanner.reservationms.exceptionhandler;


import com.eventplanner.reservationms.exceptions.BadRequestException;
import com.eventplanner.reservationms.exceptions.ForbiddenException;
import com.eventplanner.reservationms.exceptions.RessourseNotFoundException;
import com.eventplanner.reservationms.payload.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> badRequestException(BadRequestException e){
        return  new ResponseEntity<>(e.getError(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorResponse>forbiddenException(ForbiddenException e){
        return  new ResponseEntity<>(e.getError(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(RessourseNotFoundException.class)
    public ResponseEntity<ErrorResponse>ressourceNotFoundException(RessourseNotFoundException e){
        return  new ResponseEntity<>(e.getError(), HttpStatus.BAD_REQUEST);
    }


}
