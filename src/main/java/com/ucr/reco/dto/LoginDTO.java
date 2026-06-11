package com.ucr.reco.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.jspecify.annotations.NonNull;

public class LoginDTO {
    @Email(message = "Email debe ser válido")
    @NotBlank(message = "Email no puede estar vacío")
    private String email;

    @NotBlank(message = "Password no puede estar vacío")
    private String password;

    public LoginDTO() {
    }

    public LoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public @Email(message = "Email debe ser válido") @NotBlank(message = "Email no puede estar vacío") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Email debe ser válido") @NotBlank(message = "Email no puede estar vacío") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Password no puede estar vacío") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password no puede estar vacío") String password) {
        this.password = password;
    }
}
