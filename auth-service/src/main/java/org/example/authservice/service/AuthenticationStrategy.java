package org.example.authservice.service;

import org.example.authservice.dto.request.UserDtoLog;
import org.example.authservice.dto.response.AuthResponse;

public interface AuthenticationStrategy {
    AuthResponse authenticate(UserDtoLog log);

}
