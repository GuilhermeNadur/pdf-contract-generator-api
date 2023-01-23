package com.pdfcontractgeneratorapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessageDTO {

    private int statusCode;
    private LocalDateTime localDateTime;
    private String message;
    private String stackTrace;
    private String description;
}
