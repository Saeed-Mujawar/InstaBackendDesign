package com.geekster.Insta.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInInput {

    @Email
    String email;

    @Size(min = 8, max = 20, message = "Password length must be between 8 and 20 characters")
    String password;
}
