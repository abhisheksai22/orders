package com.abhi.orders.service;

import com.abhi.orders.entity.UserDetail;
import com.abhi.orders.model.UserDetailRequestDto;
import com.abhi.orders.model.UserDetailResponseDto;

import java.util.List;

public interface UserDetailService {
    UserDetailResponseDto createUserDetails(UserDetailRequestDto dto);

    List<UserDetailResponseDto> getAllUserDetails();
}
