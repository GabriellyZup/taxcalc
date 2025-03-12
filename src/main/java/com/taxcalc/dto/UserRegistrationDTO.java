package com.taxcalc.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(name = "UserRegistration", description = "DTO for user registration request")
public class UserRegistrationDTO {

    @NotBlank(message = "O nome de usuário é obrigatório")
    @Schema(description = "Unique username", example = "admin123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    @NotBlank(message = "A senha é obrigatória")
    @Schema(description = "User password", example = "securePassword123!", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

    @Schema(description = "User role", example = "USER", allowableValues = {"USER", "ADMIN"})
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}