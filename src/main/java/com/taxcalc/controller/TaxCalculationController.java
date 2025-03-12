package com.taxcalc.controller;

import com.taxcalc.dto.CalculationRequestDTO;
import com.taxcalc.dto.CalculationResponseDTO;
import com.taxcalc.service.TaxCalculationService;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.math.BigDecimal;




@Tag(name = "Tax Calculation", description = "Endpoints para operações de cálculo de impostos"

)
@RestController
@RequestMapping("/calc")
public class TaxCalculationController {

    private final TaxCalculationService taxCalculationService;

    public TaxCalculationController(TaxCalculationService taxCalculationService) {
        this.taxCalculationService = taxCalculationService;
    }

    @Operation(
            summary = "Calcular o valor do imposto",
            description ="Calcula o imposto com base no tipo de imposto e no valor base (requer papel ADMIN)"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cálculo bem-sucedido"),
            @ApiResponse(responseCode = "403", description =  "Proibido: Papel ADMIN requerido"),
            @ApiResponse(responseCode = "404", description = "Tipo de imposto não encontrado")
    })
    @PostMapping
    public CalculationResponseDTO calculateTax(
            @RequestBody CalculationRequestDTO request,
            @RequestHeader("X-User-Role")
            @Parameter(
                    description = "User role header",
                    example = "ADMIN",
                    required = true
            ) String role
    ) {
        if (!"ADMIN".equals(role) ) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso restrito a ADMIN");
        }

        BigDecimal taxAmount = taxCalculationService.calculateTax(
                request.getTaxTypeId(),
                request.getBaseValue()
        );

        return taxCalculationService.buildCalculationResponse(
                request.getTaxTypeId(),
                request.getBaseValue(),
                taxAmount
        );
    }
}