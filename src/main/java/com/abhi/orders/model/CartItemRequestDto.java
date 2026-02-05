package com.abhi.orders.model;

import lombok.*;

import java.util.HashMap;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItemRequestDto {

    private Long orderDetailId;
    private HashMap<Long, Integer> productQuantity;

}
