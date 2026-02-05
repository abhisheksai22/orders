package com.abhi.orders.validations;

import com.abhi.orders.exceptions.ResourceValidationException;
import com.abhi.orders.model.ProductPatchRequestBodyDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class ProductValidation {

    private static final Set<String> ALLOWED_SORT_FIELDS = Set.of(
            "price",
            "inventoryId",
            "createdTimestamp",
            "id"
    );

    public void checkProductPatchRequestBodyDtoValid(ProductPatchRequestBodyDto dto) {

        if (dto.getDescription() == null && dto.getInventoryId() == null && dto.getPrice() == null) {
            List<String> errorList = new ArrayList<>();

            throw new ResourceValidationException("The given request body is null", errorList);
        }

    }

    public void top5AttributeCheck(String by) {
        if (!ALLOWED_SORT_FIELDS.contains(by)) {
            throw new IllegalArgumentException("Invalid sort field: " + by);
        }
    }
}
