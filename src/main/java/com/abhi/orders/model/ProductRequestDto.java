package com.abhi.orders.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {

    private String description;
    @NotNull
    private Double price;
    @NotNull
    private Long inventoryId;

}
