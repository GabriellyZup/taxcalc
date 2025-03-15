//package com.taxcalc.service;
//
//import com.taxcalc.dto.UserRegistrationDTO;
//import com.taxcalc.dto.UserResponseDTO;
//import com.taxcalc.exception.DuplicateResourceException;
//import com.taxcalc.model.User;
//import com.taxcalc.repository.UserRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.NullAndEmptySource;
//import org.junit.jupiter.params.provider.ValueSource;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.mindrot.jbcrypt.BCrypt;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class UserServiceTest {
//
//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private UserService userService;
//
//    // Testes Positivos
//    @Test
//    void registerUser_ShouldReturnUserResponse_WhenValidInput() {
//        // Arrange
//        UserRegistrationDTO dto = new UserRegistrationDTO("user1", "securePass123", "USER");
//        User savedUser = new User(1L, "user1", "hashedPass", "USER");
//
//        when(userRepository.existsByUsername("user1")).thenReturn(false);
//        when(userRepository.save(any())).thenReturn(savedUser);
//
//        // Act
//        UserResponseDTO response = userService.registerUser(dto);
//
//        // Assert
//        assertAll(
//                () -> assertEquals(1L, response.getId()),
//                () -> assertEquals("user1", response.getUsername()),
//                () -> assertEquals("USER", response.getRole())
//        );
//        verify(userRepository).save(any());
//    }
//
//    // Testes Negativos
//    @ParameterizedTest
//    @NullAndEmptySource
//    @ValueSource(strings = {" ", "\t", "\n"})
//    void registerUser_ShouldThrowWhenUsernameInvalid(String username) {
//        // Arrange
//        UserRegistrationDTO dto = new UserRegistrationDTO(username, "password123", "USER");
//
//        // Act & Assert
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
//                () -> userService.registerUser(dto));
//
//        assertEquals("O nome de usuário é obrigatório", exception.getMessage());
//    }
//
//    @Test
//    void registerUser_ShouldThrowWhenUsernameExists() {
//        // Arrange
//        UserRegistrationDTO dto = new UserRegistrationDTO("existingUser", "pass123", "USER");
//        when(userRepository.existsByUsername("existingUser")).thenReturn(true);
//
//        // Act & Assert
//        DuplicateResourceException exception = assertThrows(DuplicateResourceException.class,
//                () -> userService.registerUser(dto));
//
//        assertEquals("Username já está em uso", exception.getMessage());
//        verify(userRepository, never()).save(any());
//    }
//
//    @ParameterizedTest
//    @ValueSource(strings = {"short", "1234", ""})
//    @NullAndEmptySource
//    void registerUser_ShouldThrowWhenWeakPassword(String weakPassword) {
//        // Arrange
//        UserRegistrationDTO dto = new UserRegistrationDTO("user1", weakPassword, "USER");
//
//        // Act & Assert
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
//                () -> userService.registerUser(dto));
//
//        assertEquals("Senha deve ter pelo menos 8 caracteres", exception.getMessage());
//    }
//
//    @Test
//    void registerUser_ShouldHashPassword() {
//        // Arrange
//        UserRegistrationDTO dto = new UserRegistrationDTO("user1", "mySecretPassword", "USER");
//        when(userRepository.existsByUsername("user1")).thenReturn(false);
//        when(userRepository.save(any())).thenAnswer(invocation -> {
//            User user = invocation.getArgument(0);
//            return new User(1L, user.getUsername(), user.getPassword(), user.getRole());
//        });
//
//        // Act
//        UserResponseDTO response = userService.registerUser(dto);
//
//        // Assert
//        verify(userRepository).save(argThat(user ->
//                BCrypt.checkpw("mySecretPassword", user.getPassword())
//        ));
//    }
//}