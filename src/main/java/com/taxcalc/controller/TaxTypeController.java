package com.taxcalc.controller;

import com.taxcalc.model.TaxType;
import com.taxcalc.repository.TaxTypeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/tipos")
public class TaxTypeController {
    private final TaxTypeRepository taxTypeRepository;

    public TaxTypeController(TaxTypeRepository taxTypeRepository) {
        this.taxTypeRepository = taxTypeRepository;
    }

    // ENDPOINT 1: Listar todos
    @GetMapping
    public List<TaxType> getAllTaxTypes() {
        return taxTypeRepository.findAll();
    }

    // ENDPOINT 2: Buscar por ID
    @GetMapping("/{id}")
    public TaxType getTaxTypeById(@PathVariable Long id) {
        return taxTypeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tax type not found"));
    }

    // ENDPOINT 3: Criar (somente ADMIN)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaxType createTaxType(
            @RequestBody TaxType taxType,
            @RequestHeader("X-User-Role") String role // ← Validação de role via header
    ) {
        // Validação de role
        if (!"ADMIN".equals(role)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Apenas ADMIN pode executar esta ação");
        }

        // Validação de alíquota
        if (taxType.getTaxRate() < 0 || taxType.getTaxRate() > 100) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Alíquota inválida (0-100%)");
        }

        return taxTypeRepository.save(taxType);
    }

    // ENDPOINT 4: Excluir (somente ADMIN)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTaxType(
            @PathVariable Long id,
            @RequestHeader("X-User-Role") String role // ← Validação de role
    ) {
        if (!"ADMIN".equals(role)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Apenas ADMIN pode excluir impostos");
        }

        taxTypeRepository.deleteById(id);
    }
}