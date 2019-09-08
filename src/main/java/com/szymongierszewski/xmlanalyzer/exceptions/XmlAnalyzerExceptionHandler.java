package com.szymongierszewski.xmlanalyzer.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class XmlAnalyzerExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(XmlAnalyzerExceptionHandler.class);

    @ExceptionHandler(XmlContentException.class)
    public ResponseEntity<ExceptionMessage> handleXmlAttributeValueException(Exception e) {
        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        ExceptionMessage exceptionMessage = new ExceptionMessage(httpStatus.value(), httpStatus.getReasonPhrase(), e.getMessage());
        LOGGER.warn("XmlContentException has been handled. ExceptionMessage \"{}\" with HTTP status code {} has been created. StackTrace: ", exceptionMessage.getMessage(), exceptionMessage.getStatusCode(), e);
        return new ResponseEntity<>(exceptionMessage, httpStatus);
    }

    @ExceptionHandler(XmlProcessingException.class)
    public ResponseEntity<ExceptionMessage> handleXmlProcessingException(Exception e) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ExceptionMessage exceptionMessage = new ExceptionMessage(httpStatus.value(), httpStatus.getReasonPhrase(), e.getMessage());
        LOGGER.warn("XmlProcessingException has been handled. ExceptionMessage \"{}\" with HTTP status code {} has been created. StackTrace: ", exceptionMessage.getMessage(), exceptionMessage.getStatusCode(), e);
        return new ResponseEntity<>(exceptionMessage, httpStatus);
    }
}
