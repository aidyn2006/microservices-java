package org.example.userprofileservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.userprofileservice.dto.UserProfileDto;
import org.example.userprofileservice.entity.UserProfile;
import org.example.userprofileservice.repository.UserProfileRepository;
import org.example.userprofileservice.service.UserProfileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;

    @Transactional
    public String saveUserData(UserProfileDto userProfileDto){
        UserProfile userProfile = UserProfile.builder()
                .userId(userProfileDto.getUserId())
                .firstname(userProfileDto.getFirstname())
                .phoneNumber(userProfileDto.getPhoneNumber())
                .lastname(userProfileDto.getLastname())
                .build();
        userProfileRepository.save(userProfile);
        return "Профиль создан";
    }

    public UserProfileDto getUser(Long userId){
        UserProfile userProfile = userProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        return UserProfileDto.builder()
                .userId(userProfile.getUserId())
                .firstname(userProfile.getFirstname())
                .lastname(userProfile.getLastname())
                .phoneNumber(userProfile.getPhoneNumber())
                .build();
    }
}
