package com.abhi.orders.model;

public record CartItemResponseDto(Long id,
                                  Integer quantity,
                                  Double priceAtPurchase) {
}
