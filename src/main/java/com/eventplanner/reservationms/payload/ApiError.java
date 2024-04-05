package com.eventplanner.reservationms.payload;

public enum ApiError {

    UNAUTHORIZED_USER(41, "Invalid credentiels", "The requested service needs credentials, and the ones provided are invalid or expired."),
    RESSOUCE_NOT_FOUND(60, "Resource not found", "The requested URI or the requested resource does not exist."),
    USER_NOT_FOUND(60, "Resource not found", "The USER does not exist."),

    EVENT_NOT_FOUND(60, "Resource not found", "The EVENT does not exist."),
    EVENT_FULL(45, "Resource not available", "Event at capacity, unable to accommodate additional guests."),

    HEADER_AUTHORIZATION_ERROR(60, "Invalid credentiels", "The header authorization must contains a valid barer token "),

    USER_DELETING_NOT_AUTHORISED(60, "Invalid credentiels", "The user can't be deleted ."),


    USER_NOT_AUTHORISED(41, "Forbidden user", "User is not authorized to access this resource.");

    private Integer errorCode;
    private String message;
    private String description;

    private ApiError(Integer errorCode, String message, String description) {
        this.errorCode = errorCode;
        this.message = message;
        this.description = description;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}