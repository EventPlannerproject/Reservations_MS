package com.eventplanner.reservationms.exceptions;


public class RessourseNotFoundException extends RuntimeException {
    private final transient ErrorResponse error;





    public RessourseNotFoundException(ErrorResponse error) {
        super(error.getMessage());
        this.error = error;
    }

    public ErrorResponse getError() {
        return error;
    }
}
