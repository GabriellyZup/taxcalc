package com.taxcalc.controller;

import com.taxcalc.dto.UserRegistrationDTO;
import com.taxcalc.dto.UserResponseDTO;
import com.taxcalc.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO register(@RequestBody UserRegistrationDTO dto) {
        // Validação adicional
        if (dto.getUsername() == null || dto.getUsername().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username é obrigatório");
        }

        return userService.registerUser(dto);
    }
}