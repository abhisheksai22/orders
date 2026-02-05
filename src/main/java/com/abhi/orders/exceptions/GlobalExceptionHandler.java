package com.abhi.orders.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;
import java.util.List;

import static com.abhi.orders.filters.RequestIdFilter.MDC_REQUEST_ID_KEY;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex) {
        log.error("handleResourceAlreadyExistsException : {}", ex.toString());
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
                .xRequestId(ex.xRequestId)
                .error(ex.getConflictList().toString())
                .message(ex.getMessage())
                .createdTimeStamp(OffsetDateTime.now())
                .build();
        log.debug("apiErrorResponse : {}", apiErrorResponse);
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        log.error("handleResourceNotFoundException : {}", ex.toString());
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
                .xRequestId(MDC.get(MDC_REQUEST_ID_KEY))
                .message(ex.getMessage())
                .createdTimeStamp(OffsetDateTime.now())
                .build();

        return new ResponseEntity<>(apiErrorResponse, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(ResourceValidationException.class)
    public ResponseEntity<ApiErrorResponse> handleResourceValidationException(ResourceValidationException ex) {
        log.error("handleResourceValidationException : {}", ex.toString());
        List<String> errorList = ex.getErrorList();

        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
                .xRequestId(MDC.get(MDC_REQUEST_ID_KEY))
                .message(ex.getMessage())
                .createdTimeStamp(OffsetDateTime.now())
                .build();
        if (!errorList.isEmpty()) {
            apiErrorResponse.setError(errorList.toString());
        }

        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);

    }
}
