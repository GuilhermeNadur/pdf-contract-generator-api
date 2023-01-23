package com.pdfcontractgeneratorapi.util;

import com.pdfcontractgeneratorapi.dto.ErrorMessageDTO;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

public class MessageUtils {

    private MessageUtils() {}

    public static ErrorMessageDTO getErrorMessageDTO(Exception exception, WebRequest request, HttpStatus httpStatus, LocalDateTime localDateTime) {
        return ErrorMessageDTO.builder()
                .statusCode(httpStatus.value())
                .localDateTime(localDateTime)
                .message(exception.getMessage())
                .stackTrace(ExceptionUtils.getStackTrace(exception))
                .description(request.getDescription(false))
                .build();
    }
}
