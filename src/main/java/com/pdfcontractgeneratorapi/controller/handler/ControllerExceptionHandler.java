package com.pdfcontractgeneratorapi.controller.handler;

import com.pdfcontractgeneratorapi.dto.ErrorMessageDTO;
import com.pdfcontractgeneratorapi.util.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(FileNotFoundException.class)
    public ErrorMessageDTO investmentNotFoundExceptionHandler(FileNotFoundException exception, WebRequest request) {
        return MessageUtils.getErrorMessageDTO(exception, request, HttpStatus.NOT_FOUND, LocalDateTime.now());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorMessageDTO exceptionHandler(Exception exception, WebRequest request) {
        log.error(ExceptionUtils.getStackTrace(exception));
        return MessageUtils.getErrorMessageDTO(exception, request, HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }
}
