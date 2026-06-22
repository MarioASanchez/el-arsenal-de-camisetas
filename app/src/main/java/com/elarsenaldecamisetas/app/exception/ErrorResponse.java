package com.elarsenaldecamisetas.app.exception;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private int status;
    private String message;
    private LocalDateTime timestamp;

    private List <FieldError> errors;

    @Data
    @AllArgsConstructor
    public static class FieldError{
        private String field;
        private String message;
    }

}
