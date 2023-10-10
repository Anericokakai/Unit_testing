package com.unittest.unittesting.tdo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class UserResponse {
    private  String name;
    private String email;
    private String role;
    private int id;


}
