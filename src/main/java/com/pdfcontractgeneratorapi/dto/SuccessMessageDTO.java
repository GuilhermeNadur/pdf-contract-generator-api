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
public class SuccessMessageDTO {

    private int statusCode;
    private LocalDateTime localDateTime;
    private String message;
}
