package org.example.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.authservice.dto.request.UserDtoLog;
import org.example.authservice.dto.request.UserDtoReg;
import org.example.authservice.dto.response.AuthResponse;
import org.example.authservice.entity.User;
import org.example.authservice.repository.UserRepository;
import org.example.authservice.security.jwt.JwtService;
import org.example.authservice.service.AuthService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final JavaMailSender mailSender;

    private final Map<String, String> verificationCodes = new HashMap<>();
    private final Map<String, User> pendingUsers = new HashMap<>();

    private final Map<String,String> passwordChanges=new HashMap<>();

    @Override
    public AuthResponse login(UserDtoLog request) throws Exception {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new Exception("Пользователь не найден!"));

        if (passwordEncoder.matches(request.getPassword(), user.getPassword()) && user.getEmail().equals(request.getEmail())) {
            var jwtToken = jwtService.generateToken(user.getUsername());
            return AuthResponse.builder()
                    .accessToken(jwtToken)
                    .build();
        } else {
            throw new Exception("Данные введены неправильно!");
        }
    }
    @Transactional
    @Override
    public String registration(UserDtoReg register) throws Exception {
        if (userRepository.findByEmail(register.getEmail()).isPresent()) {
            throw new Exception("Пользователь с email: " + register.getEmail() + " уже существует!");
        }

        String verificationCode = generateVerificationCode();
        sendVerificationEmail(register.getEmail(), verificationCode);

        User user = User.builder()
                .username(register.getUsername())
                .phoneNumber(register.getPhoneNumber())
                .createdAt(LocalDateTime.now())
                .password(passwordEncoder.encode(register.getPassword()))
                .role(register.getRole())
                .email(register.getEmail())
                .build();

        pendingUsers.put(register.getEmail(), user);
        verificationCodes.put(register.getEmail(), verificationCode);

        return "Код подтверждения был отправлен на вашу почту. Пожалуйста, подтвердите регистрацию.";
    }

    @Override
    public String confirmRegistration(String email, String verificationCode) throws Exception {
        Optional<User> pendingUser = Optional.ofNullable(pendingUsers.get(email));
        if (!pendingUser.isPresent()) {
            throw new Exception("Пользователь с таким email не найден или уже подтвержден.");
        }
        String savedCode = verificationCodes.get(email);
        if (savedCode == null || !savedCode.equals(verificationCode)) {
            throw new Exception("Неверный код подтверждения.");
        }

        userRepository.save(pendingUser.get());

        pendingUsers.remove(email);
        verificationCodes.remove(email);

        return "Ваш аккаунт успешно подтвержден!";
    }

    private String generateVerificationCode() {
        Random random = new Random();
        int code = random.nextInt(89999) + 10000;
        return String.valueOf(code);
    }

    private void sendVerificationEmail(String email, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("nurlan.aydin06nnn@mail.ru");
        message.setTo(email);
        message.setSubject("Подтверждение регистрации");
        message.setText("Ваш код подтверждения: " + code);
        mailSender.send(message);
    }
    public String changePassword(String email){
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (!userOptional.isPresent()) {
            throw new RuntimeException("Такой пользователь нет в нашей базе данных");
        }
        else {
            String verificationCode = generateVerificationCode();
            sendVerificationEmail(email, verificationCode);

            passwordChanges.put(email, verificationCode);

            return "Код подтверждения для изменения пароля был отправлен на вашу почту.";
        }
    }

    public String confirmPassword(String email, String verificationCode, String newPassword) throws Exception {
        String savedCode=passwordChanges.get(email);
        if(savedCode==null || !savedCode.equals(verificationCode)){
            throw new Exception("Неверный код подтверждения для изменения пароля.");
        }
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден."));
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        passwordChanges.remove(email);
        return "Пороль успешно изменен";
    }





}
