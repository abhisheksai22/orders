package com.abhi.orders.service.impl;

import com.abhi.orders.entity.UserDetail;
import com.abhi.orders.exceptions.ResourceAlreadyExistsException;
import com.abhi.orders.factory.UserDetailFactory;
import com.abhi.orders.model.UserDetailRequestDto;
import com.abhi.orders.model.UserDetailResponseDto;
import com.abhi.orders.repo.UserDetailRepository;
import com.abhi.orders.repo.projections.ConflictCountProjection;
import com.abhi.orders.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.abhi.orders.constants.UserDetailConstants.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailServiceImpl implements UserDetailService {

    private final UserDetailFactory userDetailFactory;
    private final UserDetailRepository userDetailRepository;

    @Override
    @Transactional
    public UserDetailResponseDto createUserDetails(UserDetailRequestDto dto) {
        UserDetail userDetail = userDetailFactory.userDetailRequestToEntity(dto);
        ConflictCountProjection conflicts =
                userDetailRepository.findConflicts(dto.getEmail(), dto.getPhoneNumber());
        List<String> phoneOrEmailExistList = phoneOrEmailExists(conflicts, dto);
        if (!phoneOrEmailExistList.isEmpty()) {
            throw new ResourceAlreadyExistsException(USER_ALREADY_EXISTS, phoneOrEmailExistList);
        }
        log.debug("conflicts : {}", conflicts);
        userDetailRepository.save(userDetail);
        return userDetailFactory.userDetailToResponse(userDetail);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDetailResponseDto> getAllUserDetails() {
        return userDetailRepository.findAll().stream()
                .map(userDetailFactory::userDetailToResponse)
                .toList();
    }

    private List<String> phoneOrEmailExists(ConflictCountProjection conflicts, UserDetailRequestDto dto) {
        List<String> conflictsList = new ArrayList<>();
        if (conflicts.getPhoneCount() > 0) {
            conflictsList.add(PHONE_ALREADY_EXISTS + dto.getPhoneNumber());
        }
        if (conflicts.getEmailCount() > 0) {
            conflictsList.add(EMAIL_ID_ALREADY_EXISTS + dto.getEmail());
        }
        log.debug("conflictsList : {}", conflictsList);
        return conflictsList;
    }
}
