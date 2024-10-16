package org.example.userprofileservice.controller;


import lombok.RequiredArgsConstructor;
import org.example.userprofileservice.dto.UserProfileDto;
import org.example.userprofileservice.service.UserProfileService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user-profile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PostMapping("/create-user-profile")
    public String create(@RequestBody UserProfileDto userProfileDto){
        return userProfileService.saveUserData(userProfileDto);
    }

    @GetMapping("/get-user-profile/{userId}")
    public UserProfileDto getUser(@PathVariable Long userId){
        return userProfileService.getUser(userId);
    }

}
