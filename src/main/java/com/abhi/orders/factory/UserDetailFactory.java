package com.abhi.orders.factory;

import com.abhi.orders.entity.UserDetail;
import com.abhi.orders.mapper.UserDetailMapper;
import com.abhi.orders.model.UserDetailRequestDto;
import com.abhi.orders.model.UserDetailResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
@Slf4j
public class UserDetailFactory {

    public UserDetail userDetailRequestToEntity(UserDetailRequestDto dto) {

        return UserDetailMapper.userDetailRequestToEntity(dto, OffsetDateTime.now());

    }

    public UserDetailResponseDto userDetailToResponse(UserDetail userDetail) {

        return UserDetailMapper.userDetailToResponse(userDetail);

    }
}
