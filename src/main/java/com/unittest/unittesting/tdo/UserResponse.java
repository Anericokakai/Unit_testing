package com.unittest.unittesting.tdo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UserResponse {
    private  String name;
    private String email;
    private String role;
    private int id;


}
