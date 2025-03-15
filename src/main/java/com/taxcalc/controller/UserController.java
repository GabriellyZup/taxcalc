package com.taxcalc.controller;

import com.taxcalc.dto.UserRegistrationDTO;
import com.taxcalc.dto.UserResponseDTO;
import com.taxcalc.service.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User Management", description = "Registro de usuario e criação de endpoints")
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Registrar novo usuario",
            description = "Criar um novo usuario com nome e password"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuario criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Entrada de dados invalida")
    })

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO register(@RequestBody UserRegistrationDTO dto) {
        //UserService userService = new UserService();
        if (dto.getRole() == null || !dto.getRole().matches("ADMIN|USER")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Role inválida (use ADMIN ou USER)");
        }
        if (dto.getUsername() == null || dto.getUsername().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username é obrigatório");
        }

        return userService.registerUser(dto);
    }
}