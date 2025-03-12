package com.taxcalc.repository;

import com.taxcalc.model.User;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.stereotype.Repository;

@Repository
//@Hidden // Esconde o repositório no Swagger (não é necessário documentar métodos CRUD básicos)
public interface UserRepository extends JpaRepository<User, Long> {

    @Operation(
            summary = "Find user by username",
            description = "Returns a user by their username"
    )
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    List<User> findAllByRole(String role);
}