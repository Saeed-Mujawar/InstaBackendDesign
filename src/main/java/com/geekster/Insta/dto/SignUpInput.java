package com.geekster.Insta.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpInput {


    private String firstName;
    private String lastName;
    private Integer age;
    @Email
    private String email;
    @Size(min = 8, max = 20, message = "Password length must be between 8 and 20 characters")
    private String userPassword;
    @Length(min = 10, max = 12)
    private String phoneNumber;
}
