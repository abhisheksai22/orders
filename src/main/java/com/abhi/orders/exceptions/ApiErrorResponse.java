package com.abhi.orders.exceptions;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class ApiErrorResponse {

    private String xRequestId;
    private String message;
    private String error;
    private OffsetDateTime createdTimeStamp;

}
