package org.example.userprofileservice.service;

import org.example.userprofileservice.dto.UserProfileDto;

public interface UserProfileService {
    String saveUserData(UserProfileDto userProfileDto);
    UserProfileDto getUser(Long userId);
}
