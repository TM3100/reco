package com.ucr.reco.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UserDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;
    @Email
    @NotBlank(message = "El email no puede estar vacío")
    private String email;
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()]).{8,}$",
            message = "La contraseña debe tener al menos 8 caracteres, una mayúscula, una minúscula, un número, y un símbolo.")
    private String password;
    private String role;

    public UserDTO() {
    }

    public UserDTO(String name, String email, String password, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public @NotBlank(message = "El nombre no puede estar vacío") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "El nombre no puede estar vacío") String name) {
        this.name = name;
    }

    public @Email @NotBlank(message = "El email no puede estar vacío") String getEmail() {
        return email;
    }

    public void setEmail(@Email @NotBlank(message = "El email no puede estar vacío") String email) {
        this.email = email;
    }

    public @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()]).{8,}$",
            message = "La contraseña debe tener al menos 8 caracteres, una mayúscula, una minúscula, un número, y un símbolo.") String getPassword() {
        return password;
    }

    public void setPassword(@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()]).{8,}$",
            message = "La contraseña debe tener al menos 8 caracteres, una mayúscula, una minúscula, un número, y un símbolo.") String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
