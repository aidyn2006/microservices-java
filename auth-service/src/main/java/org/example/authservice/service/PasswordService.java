package org.example.authservice.service;

import org.example.authservice.dto.request.PasswordChangeRequest;

public interface PasswordService {
    String changePassword(String email);
    String confirmPassword(PasswordChangeRequest request);
}
