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

    @Operation(
            summary = "Obter tipo de imposto por ID",
            description = "Retorna os detalhes de um tipo de imposto específico"





    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tipo de imposto encontrado"),
            @ApiResponse(responseCode = "404", description = "Tipo de imposto não encontrado")
    })
    @GetMapping("/{id}")
    public TaxTypeResponseDTO getTaxTypeById(@PathVariable Long id) {
        return taxTypeRepository.findById(id) // Método do JpaRepository
                .map(TaxTypeResponseDTO::new)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de imposto não encontrado"));
    }

    @Operation(
            summary = "Criar novo tipo de imposto",
            description = "Registra um novo tipo de imposto (ADMIN role requerido)"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Tipo de imposto criado com sucesso"),
            @ApiResponse(responseCode = "403", description = "Bloqueado: ADMIN role requerido"),
            @ApiResponse(responseCode = "400", description = "Entrada de dados invalida")
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
        taxType.setNome(request.getNome());
        taxType.setDescricao(request.getDescricao());
        taxType.setAliquota(request.getAliquota());

        TaxType savedTaxType = taxTypeRepository.save(taxType); // Método do JpaRepository
        return new TaxTypeResponseDTO(savedTaxType);
    }


    @Operation(
            summary = "Excluir tipo de imposto",
            description = "Deleta tipo de imposto por ID (ADMIN role requerido)"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Tipo de imposto excluido com sucesso"),
            @ApiResponse(responseCode = "403", description = "Bloqueado: ADMIN role requerido"),
            @ApiResponse(responseCode = "404", description = "Tipo de imposto não encontrado")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTaxType(
            @PathVariable
            @Parameter(description = "ID do tipo de imposto excluido", example = "1")
            Long id,
            @RequestHeader("X-User-Role")
            @Parameter(
                    description = "User role header",
                    example = "ADMIN",
                    required = true
            )String role
    ) {
        if (role == null || !role.equals("ADMIN")) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Apenas ADMIN pode excluir impostos");
        }

        if (!taxTypeRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de imposto não encontrado");
        }

        taxTypeRepository.deleteById(id); // Método do JpaRepository
    }
}