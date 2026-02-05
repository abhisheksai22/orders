package com.abhi.orders.controller;

import com.abhi.orders.model.CartItemRequestDto;
import com.abhi.orders.model.CartItemResponseDto;
import com.abhi.orders.service.CartItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.abhi.orders.filters.RequestIdFilter.REQUEST_ID_HEADER;

@RestController
@RequestMapping("/api/v1/cat-items")
@RequiredArgsConstructor
@Slf4j
public class CartItemController {

    @PostMapping
    ResponseEntity<CartItemResponseDto> createCartItem(@RequestHeader(REQUEST_ID_HEADER) String xRequestId,
                                                       @RequestBody CartItemRequestDto dto) {
        log.info("Entering createCartItem method: CartItemRequestDto : {}", dto);
        return new ResponseEntity<>(CartItemService.createCartItem(dto), HttpStatus.CREATED);
    }

}
