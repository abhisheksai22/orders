package com.abhi.orders.controller;

import com.abhi.orders.model.ProductPatchRequestBodyDto;
import com.abhi.orders.model.ProductRequestDto;
import com.abhi.orders.model.ProductResponseDto;
import com.abhi.orders.service.ProductService;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.abhi.orders.filters.RequestIdFilter.REQUEST_ID_HEADER;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @PostMapping
    ResponseEntity<ProductResponseDto> createProduct(@RequestHeader(REQUEST_ID_HEADER) String xRequestId,
                                                     @RequestBody ProductRequestDto dto) {
        log.info("Entering createProduct method: ProductRequestDto : {}", dto);
        return new ResponseEntity<>(productService.createProduct(dto), HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<List<ProductResponseDto>> getAllProduct(@RequestHeader(REQUEST_ID_HEADER) String xRequestId) {
        log.info("Entering get All Product method...");
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<ProductResponseDto> getProductById(@RequestHeader(REQUEST_ID_HEADER) String xRequestId,
                                                      @PathVariable("id") Long id) {
        log.info("Entering getProductById method in controller...");

        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @GetMapping("/top5")
    ResponseEntity<List<ProductResponseDto>> getTop5ProductBy(@RequestHeader(REQUEST_ID_HEADER) String xRequestId,
                                                              @RequestParam("by") String by) {
        log.info("Entering getTop5ProductBy method in controller...");

        return new ResponseEntity<>(productService.getTop5ProductBy(by), HttpStatus.OK);
    }


    @PatchMapping("/{id}")
    ResponseEntity<?> updateProductById(@RequestHeader(REQUEST_ID_HEADER) String xRequestId,
                                        @PathVariable("id") Long id,
                                        @RequestBody ProductPatchRequestBodyDto dto) {
        log.info("Entering updateProductById method in controller...");
        productService.updateProductById(id, dto);
        return new ResponseEntity<>(HttpStatus.valueOf(204));
    }


}
