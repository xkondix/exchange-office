package com.kowalczyk.konrad.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorLogResponse {

    private String message;
    private String uri;
    private LocalDateTime timestamp;
    private String method;
    private String className;
    private HttpStatus status;

}
