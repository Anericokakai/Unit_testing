package com.unittest.unittesting.tdo;

import com.unittest.unittesting.models.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientRequest {
@NotNull(message = "username cannot be empty")
    private String name;
@Email(message = "invalid email address")
    private String email;
@Length(min = 6,max = 16, message = "Password should have a minimum of 6 characters and a maximum of 16")
    private String password;

    private Role role;
}
