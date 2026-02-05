package com.abhi.orders.factory;

import com.abhi.orders.entity.Product;
import com.abhi.orders.mapper.ProductMapper;
import com.abhi.orders.model.ProductPatchRequestBodyDto;
import com.abhi.orders.model.ProductRequestDto;
import com.abhi.orders.model.ProductResponseDto;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class ProductFactory {


    public Product productRequestDtoToProduct(ProductRequestDto dto) {
        return ProductMapper.productRequestDtoToProduct(dto, OffsetDateTime.now());

    }

    public ProductResponseDto productToProductResponseDto(Product product) {
        return ProductMapper.productToProductResponseDto(product);
    }

}
