package com.taxcalc.controller;

import com.taxcalc.dto.TaxTypeRequestDTO;
import com.taxcalc.dto.TaxTypeResponseDTO;
import com.taxcalc.model.TaxType;
import com.taxcalc.repository.TaxTypeRepository;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Tax Types", description = "Endpoints para gerenciamento de tipos de impostos")
@RestController
@RequestMapping("/tipos")
public class TaxTypeController {
    private final TaxTypeRepository taxTypeRepository;

    // Injeta apenas o repositório consolidado
    public TaxTypeController(TaxTypeRepository taxTypeRepository) {
        this.taxTypeRepository = taxTypeRepository;
    }

    // ENDPOINT 1: Listar todos
    @Operation(
            summary = "Listar todos os tipos de impostos",
            description = "Retorna uma lista de todos os tipos de impostos registrados"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista recuperada com sucesso")
    })
    @GetMapping
    public List<TaxTypeResponseDTO> getAllTaxTypes() {
        return taxTypeRepository.findAll().stream()
                .map(TaxTypeResponseDTO::new)
                .collect(Collectors.toList());
    }

    // ENDPOINT 2: Buscar por ID
    @Operation(
            summary = "Obter tipo de imposto por ID",
    description = "Retorna os detalhes de um tipo de imposto específico"





    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tax type found"),
            @ApiResponse(responseCode = "404", description = "Tax type not found")
    })
    @GetMapping("/{id}")
    public TaxTypeResponseDTO getTaxTypeById(@PathVariable Long id) {
        return taxTypeRepository.findById(id) // Método do JpaRepository
                .map(TaxTypeResponseDTO::new)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de imposto não encontrado"));
    }

    // ENDPOINT 3: Criar
    @Operation(
            summary = "Create new tax type",
            description = "Registers a new tax type (ADMIN role required)"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Tax type created successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden: ADMIN role required"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaxTypeResponseDTO createTaxType(
            @RequestBody @Valid TaxTypeRequestDTO request,
            @RequestHeader("X-User-Role")
            @Parameter(
                    description = "User role header",
                    example = "ADMIN",
                    required = true
            ) String role
    ) {
        if (!"ADMIN".equals(role)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Apenas ADMIN pode executar esta ação");
        }

        TaxType taxType = new TaxType();
        taxType.setName(request.getName());
        taxType.setDescription(request.getDescription());
        taxType.setTaxRate(request.getTaxRate());

        TaxType savedTaxType = taxTypeRepository.save(taxType); // Método do JpaRepository
        return new TaxTypeResponseDTO(savedTaxType);
    }

    // ENDPOINT 4: Excluir

    @Operation(
            summary = "Delete tax type",
            description = "Deletes a tax type by ID (ADMIN role required)"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Tax type deleted successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden: ADMIN role required"),
            @ApiResponse(responseCode = "404", description = "Tax type not found")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTaxType(
            @PathVariable
            @Parameter(description = "ID of the tax type to delete", example = "1")
            Long id,
            @RequestHeader("X-User-Role")
            @Parameter(
                    description = "User role header",
                    example = "ADMIN",
                    required = true
            )String role
    ) {
        if (!"ADMIN".equals(role)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Apenas ADMIN pode excluir impostos");
        }

        if (!taxTypeRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de imposto não encontrado");
        }

        taxTypeRepository.deleteById(id); // Método do JpaRepository
    }
}