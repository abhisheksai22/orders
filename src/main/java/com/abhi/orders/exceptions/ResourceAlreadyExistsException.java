package com.abhi.orders.exceptions;

import org.slf4j.MDC;

import java.util.ArrayList;
import java.util.List;

import static com.abhi.orders.filters.RequestIdFilter.MDC_REQUEST_ID_KEY;

public class ResourceAlreadyExistsException extends RuntimeException {

    List<String> conflicts = new ArrayList<>();
    String xRequestId ;

    public ResourceAlreadyExistsException(String message, List<String> conflictsList) {
        super(message);
        this.conflicts = conflictsList;
        this.xRequestId = MDC.get(MDC_REQUEST_ID_KEY);
    }

    public List<String> getConflictList() {
        return conflicts;
    }

}
