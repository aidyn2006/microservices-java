package org.example.authservice.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.authservice.util.Role;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDtoReg {

    String email;
    String phoneNumber;
    String username;
    String password;
    Role role;
}
