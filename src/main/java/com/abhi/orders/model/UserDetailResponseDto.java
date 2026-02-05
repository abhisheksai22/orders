package com.abhi.orders.model;

import lombok.Builder;

import java.time.OffsetDateTime;

@Builder
public record UserDetailResponseDto(Long id,
                                    String address,
                                    String email,
                                    String name,
                                    String phoneNumber,
                                    OffsetDateTime createdTimestamp) {


}
