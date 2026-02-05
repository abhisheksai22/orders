package com.abhi.orders.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailRequestDto {

    String address;
    @NotNull String email;
    @NotNull String name;
    @NotNull String phoneNumber;

}
