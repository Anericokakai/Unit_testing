package com.unittest.unittesting.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")

public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
      private int id;

    @NotBlank(message = "username cannot be black")
    private String name;
    @Email(message = "email address must be valid email adress")
    private String email;
    @Length(min = 6,max = 16)

    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;



}
