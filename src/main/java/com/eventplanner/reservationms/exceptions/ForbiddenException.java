package com.eventplanner.reservationms.exceptions;


import com.eventplanner.reservationms.payload.ErrorResponse;

public class ForbiddenException extends RuntimeException{
    private final transient ErrorResponse error;



    public ForbiddenException(ErrorResponse error) {
        super(error.getMessage());
        this.error = error;
    }

    public ErrorResponse getError() {
        return error;
    }
}
