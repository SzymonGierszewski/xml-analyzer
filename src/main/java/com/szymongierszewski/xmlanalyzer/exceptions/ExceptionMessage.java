package com.szymongierszewski.xmlanalyzer.exceptions;

import java.time.LocalDateTime;

public class ExceptionMessage {

    private LocalDateTime timestamp;
    private Integer statusCode;
    private String statusName;
    private String message;

    public ExceptionMessage(Integer statusCode, String statusName, String message) {
        this.statusCode = statusCode;
        this.statusName = statusName;
        this.message = message;
    }
}
