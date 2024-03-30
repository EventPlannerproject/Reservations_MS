package com.eventplanner.reservationms.payload;

public class ErrorResponse {
    private Integer code;
    private String reason;
    private String message;

    public ErrorResponse(Integer code, String reason, String message) {
        super();
        this.code = code;
        this.reason = reason;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
