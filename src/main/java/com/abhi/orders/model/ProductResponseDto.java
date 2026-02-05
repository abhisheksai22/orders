package com.abhi.orders.model;

import lombok.Builder;

import java.time.OffsetDateTime;

@Builder
public record ProductResponseDto(Long id,
                                 String description,
                                 Double price,
                                 Long inventoryId,
                                 OffsetDateTime createdTimestamp) {
}
