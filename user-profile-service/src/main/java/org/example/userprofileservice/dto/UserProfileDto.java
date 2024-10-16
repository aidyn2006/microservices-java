package org.example.userprofileservice.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProfileDto {
    Long userId;
    String firstname;
    String lastname;
    String phoneNumber;
}
