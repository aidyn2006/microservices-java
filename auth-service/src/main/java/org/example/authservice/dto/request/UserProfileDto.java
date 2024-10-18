package org.example.authservice.dto.request;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProfileDto {
    Long userId;
    String firstname;
    String lastname;
    String phoneNumber;
}
