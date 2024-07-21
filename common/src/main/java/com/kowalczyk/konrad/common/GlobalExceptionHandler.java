package com.kowalczyk.konrad.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorLogResponse> handleAllExceptions(Exception ex, WebRequest webRequest) {
        log.error("Exception: ", ex);
        StackTraceElement[] stackTrace = ex.getStackTrace();
        String className = stackTrace.length > 0 ? stackTrace[0].getClassName() : "Unknown";
        String methodName = stackTrace.length > 0 ? stackTrace[0].getMethodName() : "Unknown";
        HttpStatus httpStatus = getHttpStatus(ex);
        String uri = "";
        if (Objects.nonNull(webRequest)) {
            uri = webRequest.getDescription(false);
        }

        ErrorLogResponse errorLogResponse = new ErrorLogResponse(ex.getMessage(), uri, LocalDateTime.now(), methodName, className, httpStatus);
        return new ResponseEntity<>(errorLogResponse, httpStatus);
    }

    private HttpStatus getHttpStatus(Exception ex) {
        if (ex instanceof MethodArgumentNotValidException) {
            return (HttpStatus) ((MethodArgumentNotValidException) ex).getStatusCode();
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

}

