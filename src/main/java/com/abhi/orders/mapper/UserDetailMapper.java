package com.abhi.orders.mapper;

import com.abhi.orders.entity.UserDetail;
import com.abhi.orders.model.UserDetailRequestDto;
import com.abhi.orders.model.UserDetailResponseDto;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.time.OffsetDateTime;

@Slf4j
@UtilityClass
public class UserDetailMapper {

    public static UserDetail userDetailRequestToEntity(UserDetailRequestDto dto, OffsetDateTime createdTimestamp) {

        return UserDetail.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .address(dto.getAddress())
                .createdTimestamp(createdTimestamp)
                .lastUpdatedTimestamp(createdTimestamp)
                .build();
    }

    public static UserDetailResponseDto userDetailToResponse(UserDetail entity) {
        UserDetailResponseDto userDetailResponseDto = UserDetailResponseDto.builder()
                .id(entity.getId())
                .address(entity.getAddress())
                .email(entity.getEmail())
                .name(entity.getName())
                .phoneNumber(entity.getPhoneNumber())
                .createdTimestamp(entity.getCreatedTimestamp())
                .build();
        log.debug("userDetailResponseDto : {}", userDetailResponseDto);
        return userDetailResponseDto;
    }
}
