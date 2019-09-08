package com.szymongierszewski.xmlanalyzer.exceptions;

import java.time.LocalDateTime;

class ExceptionMessage {

    private LocalDateTime timestamp;
    private Integer statusCode;
    private String statusName;
    private String message;

    ExceptionMessage(Integer statusCode, String statusName, String message) {
        timestamp = LocalDateTime.now();
        this.statusCode = statusCode;
        this.statusName = statusName;
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getStatusName() {
        return statusName;
    }

    public String getMessage() {
        return message;
    }
}
