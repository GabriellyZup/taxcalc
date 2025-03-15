package com.taxcalc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "UserResponse", description = "DTO response para tratamento de user")
public class UserResponseDTO {

    @JsonProperty("id")
    @Schema(description = "User ID", example = "1")
    private Long id;

    @JsonProperty("username")
    @Schema(description = "Username", example = "tax_admin")
    private String username;

    @JsonProperty("role")
    @Schema(description = "Assigned role", example = "ADMIN")
    private String role;

    public UserResponseDTO(Long id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}