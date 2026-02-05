package com.abhi.orders.repo.dao;

import com.abhi.orders.model.ProductPatchRequestBodyDto;

public interface ProductDao {
    int patchUpdateProduct(Long productId, ProductPatchRequestBodyDto dto);
}

