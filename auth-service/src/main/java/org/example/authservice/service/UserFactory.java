package org.example.authservice.service;

import org.example.authservice.dto.request.UserDtoReg;
import org.example.authservice.entity.User;


public interface UserFactory {
    User createUser(UserDtoReg userDtoReg);
}
