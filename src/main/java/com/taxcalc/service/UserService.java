package com.taxcalc.service;

import com.taxcalc.dto.UserRegistrationDTO;
import com.taxcalc.dto.UserResponseDTO;
import com.taxcalc.model.User;
import com.taxcalc.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@Tag(name = "User Service", description = "Service for user registration and management")
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Operation(
            summary = "Register a new user",
            description = "Creates a new user account with username, password, and role"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public UserResponseDTO registerUser(UserRegistrationDTO dto) {
        if (dto.getUsername() == null || dto.getUsername().isBlank()) {
            throw new IllegalArgumentException("O nome de usuário é obrigatório");
        }


        User user = new User();
        user.setUsername(dto.getUsername());

        user.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt()));

        user.setRole(dto.getRole());

        User savedUser = userRepository.save(user);
        return new UserResponseDTO(savedUser.getId(), savedUser.getUsername(), savedUser.getRole());
    }
}