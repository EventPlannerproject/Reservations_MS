package com.eventplanner.reservationms.exceptions;


import com.eventplanner.reservationms.payload.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)

public class BadRequestException extends RuntimeException{
    private final transient ErrorResponse error;

    public BadRequestException(ErrorResponse error) {
        super(error.getMessage());
        this.error = error;
    }

    public ErrorResponse getError() {
        return error;
    }

}
