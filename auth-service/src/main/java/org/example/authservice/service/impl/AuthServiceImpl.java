package org.example.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.authservice.dto.request.UserDtoLog;
import org.example.authservice.dto.request.UserDtoReg;
import org.example.authservice.dto.request.UserProfileDto;
import org.example.authservice.dto.response.AuthResponse;
import org.example.authservice.entity.User;
import org.example.authservice.exception.handling.*;
import org.example.authservice.repository.UserRepository;
import org.example.authservice.security.jwt.JwtService;
import org.example.authservice.service.AuthService;
import org.example.authservice.service.AuthenticationStrategy;
import org.example.authservice.service.EmailService;
import org.example.authservice.service.UserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final WebClient webClient;
    private final UserFactory userFactory;


    private final Map<String, String> verificationCodes = new HashMap<>();
    private final Map<String, User> pendingUsers = new HashMap<>();
    private final Map<String, UserProfileDto> storePendingUserProfile = new HashMap<>();

    private final Map<String, AuthenticationStrategy> strategies = new HashMap<>();

    @Autowired
    public AuthServiceImpl(List<AuthenticationStrategy> strategyList, UserRepository userRepository,  EmailService emailService, WebClient webClient, UserFactory userFactory) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.webClient = webClient;
        this.userFactory = userFactory;
        for (AuthenticationStrategy strategy : strategyList) {
            if (strategy instanceof EmailAuthenticationStrategy) {
                strategies.put("email", strategy);
            }
        }
    }

    public AuthResponse login(UserDtoLog log) {
        AuthenticationStrategy strategy = strategies.get("email");
        return strategy.authenticate(log);
    }

    @Transactional
    @Override
    public String registration(UserDtoReg register) {
        if (isUserExists(register.getEmail())) {
            throw new AlreadyExistException("Пользователь с email: " + register.getEmail() + " уже существует!");
        }

        User user = createUser(register);
        UserProfileDto userProfileDto = createProfile(register);

        String verificationCode = emailService.generateVerificationCode();
        emailService.sendVerificationEmail(register.getEmail(), verificationCode);

        storePendingUser(register.getEmail(), user, verificationCode);
        storePendingUserProfile.put(register.getEmail(), userProfileDto); // Сохраняем профиль для дальнейшего использования

        return "Код подтверждения был отправлен на вашу почту.";
    }

    private boolean isUserExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    private User createUser(UserDtoReg register) {
        return userFactory.createUser(register);
    }

    public UserProfileDto createProfile(UserDtoReg userDtoReg) {
        return UserProfileDto.builder()
                .firstname(userDtoReg.getFirstname())
                .lastname(userDtoReg.getLastname())
                .phoneNumber(userDtoReg.getPhoneNumber())
                .build();
    }

    private void storePendingUser(String email, User user, String verificationCode) {
        pendingUsers.put(email, user);
        verificationCodes.put(email, verificationCode);
    }

    private void createUserProfile(UserProfileDto userProfileDto) {
        webClient.post()
                .uri("http://localhost:8082/api/v1/user-profile/create-user-profile")
                .bodyValue(userProfileDto)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    @Transactional
    public String confirmRegistration(String email, String verificationCode) {
        Optional<User> pendingUser = Optional.ofNullable(pendingUsers.get(email));
        if (!pendingUser.isPresent()) {
            throw new NotFoundException("Пользователь с таким email не найден или уже подтвержден.");
        }
        String savedCode = verificationCodes.get(email);
        if (savedCode == null || !savedCode.equals(verificationCode)) {
            throw new InvalidVerificationCodeException("Неверный код подтверждения.");
        }

        User userToSave = pendingUser.get();
        userRepository.save(userToSave); // Сохраняем пользователя в БД

        UserProfileDto userProfileDto = storePendingUserProfile.get(email); // Получаем профиль пользователя
        createUserProfile(userProfileDto); // Создаем профиль пользователя

        pendingUsers.remove(email);
        verificationCodes.remove(email);
        storePendingUserProfile.remove(email); // Удаляем профиль из временного хранилища

        return "Ваш аккаунт успешно подтвержден!";
    }
}
