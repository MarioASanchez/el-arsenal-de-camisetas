package com.elarsenaldecamisetas.app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank @Email
    private String email;
    @NotBlank @Size(min=6)
    private String password;
    private String address;
    private String city;
    private String cp;
    private String country;
    private String phoneNumber;
}
