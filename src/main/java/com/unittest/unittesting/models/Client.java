package com.unittest.unittesting.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private int id;


    private String name;

    private String email;


    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;


}
