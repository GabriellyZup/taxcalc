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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Tag(name = "User Service", description = "Service para tratamento de usuários")
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Operation(
            summary = "Registrar um novo usuário",
            description = "Criar um novo usuário com username, password, e role"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Entrada de dados inválida")
    })
    public UserResponseDTO registerUser(UserRegistrationDTO dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username já existe");
        }

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