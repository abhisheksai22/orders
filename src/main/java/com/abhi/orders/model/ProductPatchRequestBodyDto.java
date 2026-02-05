package com.abhi.orders.model;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductPatchRequestBodyDto {

    private String description;
    private Double price;
    private Long inventoryId;

}
