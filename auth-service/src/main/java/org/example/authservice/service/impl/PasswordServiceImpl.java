package org.example.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.authservice.dto.request.PasswordChangeRequest;
import org.example.authservice.entity.User;
import org.example.authservice.exception.handling.InvalidVerificationCodeException;
import org.example.authservice.exception.handling.UserNotFoundException;
import org.example.authservice.repository.UserRepository;
import org.example.authservice.service.EmailService;
import org.example.authservice.service.PasswordService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PasswordServiceImpl implements PasswordService {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final Map<String,String> passwordChanges=new HashMap<>();


    public String changePassword(String email){
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("Такой пользователь нет в нашей базе данных");
        }
        else {
            String verificationCode = emailService.generateVerificationCode();
            emailService.sendVerificationEmail(email, verificationCode);

            passwordChanges.put(email, verificationCode);

            return "Код подтверждения для изменения пароля был отправлен на вашу почту.";
        }
    }

    @Transactional
    public String confirmPassword(PasswordChangeRequest request) {
        String savedCode=passwordChanges.get(request.getEmail());
        if(savedCode==null || !savedCode.equals(request.getVerificationCode())){
            throw new InvalidVerificationCodeException("Неверный код подтверждения для изменения пароля.");
        }
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден."));
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        passwordChanges.remove(request.getEmail());
        return "Пороль успешно изменен";
    }

}
