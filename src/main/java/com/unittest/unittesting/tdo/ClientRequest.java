package com.unittest.unittesting.tdo;

import com.unittest.unittesting.models.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientRequest {

    private String name;

    private String email;


    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}
