package com.abhi.orders.controller;

import com.abhi.orders.entity.UserDetail;
import com.abhi.orders.model.UserDetailRequestDto;
import com.abhi.orders.model.UserDetailResponseDto;
import com.abhi.orders.service.UserDetailService;
import jakarta.ws.rs.HeaderParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.abhi.orders.filters.RequestIdFilter.REQUEST_ID_HEADER;

@RestController
@RequestMapping("/api/v1/user-details")
@RequiredArgsConstructor
@Slf4j
public class UserDetailController {

    private final UserDetailService userDetailService;

    @PostMapping
    ResponseEntity<UserDetailResponseDto> createUserDetail(@RequestHeader(REQUEST_ID_HEADER) String xRequestId,
                                                           @RequestBody UserDetailRequestDto dto) {
        log.info("Entering createUserDetail method: UserDetailRequestDto : {}", userDetailService);
        return new ResponseEntity<>(userDetailService.createUserDetails(dto), HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<List<UserDetailResponseDto>> getAllUserDetails(@RequestHeader(REQUEST_ID_HEADER) String xRequestId) {
        return new ResponseEntity<>(userDetailService.getAllUserDetails(), HttpStatus.OK);
    }

}
