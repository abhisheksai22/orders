package com.abhi.orders.validations;

import com.abhi.orders.exceptions.ResourceValidationException;
import com.abhi.orders.model.ProductPatchRequestBodyDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidation {

    public void checkProductPatchRequestBodyDtoValid(ProductPatchRequestBodyDto dto) {

        if (dto.getDescription() == null && dto.getInventoryId() == null && dto.getPrice() == null) {
            List<String> errorList = new ArrayList<>();

            throw new ResourceValidationException("The given request body is null", errorList);
        }

    }
}
