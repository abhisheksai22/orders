package com.abhi.orders.service.impl;

import com.abhi.orders.entity.Product;
import com.abhi.orders.exceptions.ResourceNotFoundException;
import com.abhi.orders.factory.ProductFactory;
import com.abhi.orders.model.ProductPatchRequestBodyDto;
import com.abhi.orders.model.ProductRequestDto;
import com.abhi.orders.model.ProductResponseDto;
import com.abhi.orders.repo.ProductRepository;
import com.abhi.orders.service.ProductService;
import com.abhi.orders.validations.ProductValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static com.abhi.orders.constants.ProductConstants.PRODUCT_NOT_FOUND;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductFactory productFactory;
    private final ProductValidation productValidation;

    private static final Set<String> ALLOWED_SORT_FIELDS = Set.of(
            "price",
            "inventoryId",
            "createdTimestamp",
            "id"
    );


    @Override
    @Transactional
    public ProductResponseDto createProduct(ProductRequestDto dto) {
        log.info("in createProduct... dto : {}", dto);
        Product product = productFactory.productRequestDtoToProduct(dto);
        productRepository.save(product);
        log.debug("Saved Product : {}", product);
        return productFactory.productToProductResponseDto(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponseDto> getAllProducts() {
        log.info("inside getAllProducts method...");
        return productRepository.findAll().stream()
                .map(productFactory::productToProductResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponseDto getProductById(Long id) {
        log.info("inside getProductById method in service class...");

        ProductResponseDto productResponseDto = productRepository.findById(id)
                .map(productFactory::productToProductResponseDto)
                .orElseThrow(
                        () -> new ResourceNotFoundException(PRODUCT_NOT_FOUND + id)
                );
        log.debug("ProductResponseDto : {}", productResponseDto);
        return productResponseDto;
    }

    @Override
    @Transactional
    public void updateProductById(Long id, ProductPatchRequestBodyDto dto) {
        productValidation.checkProductPatchRequestBodyDtoValid(dto);
        int  rowsUpdated = productRepository.patchUpdateProduct(id, dto);
        if (rowsUpdated == 0) {
            throw new ResourceNotFoundException(PRODUCT_NOT_FOUND + id);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponseDto> getTop5ProductBy(String by) {

        if (!ALLOWED_SORT_FIELDS.contains(by)) {
            throw new IllegalArgumentException("Invalid sort field: " + by);
        }

        Pageable pageable = PageRequest.of(
                0,
                5,
                Sort.by(Sort.Direction.DESC, by)
        );

        return productRepository.findAll(pageable)
                .getContent()
                .stream()
                .map(productFactory::productToProductResponseDto)
                .toList();
    }

}
