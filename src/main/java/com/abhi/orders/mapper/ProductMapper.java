package com.abhi.orders.mapper;

import com.abhi.orders.entity.Product;
import com.abhi.orders.model.ProductPatchRequestBodyDto;
import com.abhi.orders.model.ProductRequestDto;
import com.abhi.orders.model.ProductResponseDto;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.time.OffsetDateTime;

@UtilityClass
@Slf4j
public class ProductMapper {

    public static Product productRequestDtoToProduct(ProductRequestDto dto, OffsetDateTime createdDateTime) {
        Product product = Product.builder()
                .price(dto.getPrice())
                .description(dto.getDescription())
                .inventoryId(dto.getInventoryId())
                .createdTimestamp(createdDateTime)
                .lastUpdatedTimestamp(createdDateTime)
                .build();
        log.debug("Product : {}", product);
        return product;
    }

    public static ProductResponseDto productToProductResponseDto(Product product) {
        ProductResponseDto productResponseDto = ProductResponseDto.builder()
                .id(product.getId())
                .description(product.getDescription())
                .createdTimestamp(product.getCreatedTimestamp())
                .inventoryId(product.getInventoryId())
                .price(product.getPrice())
                .build();
        log.debug("ProductResponseDto : {}", productResponseDto);
        return productResponseDto;
    }

}
