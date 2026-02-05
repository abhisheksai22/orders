package com.abhi.orders.service;

import com.abhi.orders.model.ProductPatchRequestBodyDto;
import com.abhi.orders.model.ProductRequestDto;
import com.abhi.orders.model.ProductResponseDto;

import java.util.List;

public interface ProductService {
    ProductResponseDto createProduct(ProductRequestDto dto);

    List<ProductResponseDto> getAllProducts();

    ProductResponseDto getProductById(Long id);

    void updateProductById(Long id, ProductPatchRequestBodyDto dto);

    List<ProductResponseDto> getTop5ProductBy(String by);
}
