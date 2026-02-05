package com.abhi.orders.exceptions;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ResourceValidationException extends RuntimeException {

    List<String> errorList = new ArrayList<>();

    public ResourceValidationException(String message, List<String> errorList) {
        super(message);
        this.errorList = errorList;
    }

}
