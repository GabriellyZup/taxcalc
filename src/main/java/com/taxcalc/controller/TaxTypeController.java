package com.taxcalc.controller;

import com.taxcalc.dto.TaxTypeRequest;
import com.taxcalc.dto.TaxTypeResponse;
import com.taxcalc.model.TaxType;
import com.taxcalc.repository.TaxTypeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tipos")
public class TaxTypeController {
    private final TaxTypeRepository taxTypeRepository;

    public TaxTypeController(TaxTypeRepository taxTypeRepository) {
        this.taxTypeRepository = taxTypeRepository;
    }

    // ENDPOINT 1: Listar todos (usando DTO)
    @GetMapping
    public List<TaxTypeResponse> getAllTaxTypes() {
        return taxTypeRepository.findAll().stream()
                .map(TaxTypeResponse::new)
                .collect(Collectors.toList());
    }

    // ENDPOINT 2: Buscar por ID (usando DTO)
    @GetMapping("/{id}")
    public TaxTypeResponse getTaxTypeById(@PathVariable Long id) {
        return taxTypeRepository.findById(id)
                .map(TaxTypeResponse::new)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de imposto não encontrado"));
    }

    // ENDPOINT 3: Criar (usando DTO)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaxTypeResponse createTaxType(
            @RequestBody TaxTypeRequest request,
            @RequestHeader("X-User-Role") String role
    ) {
        if (!"ADMIN".equals(role)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Apenas ADMIN pode executar esta ação");
        }

        TaxType taxType = new TaxType();
        taxType.setName(request.getName());
        taxType.setDescription(request.getDescription());
        taxType.setTaxRate(request.getTaxRate());

        TaxType savedTaxType = taxTypeRepository.save(taxType);
        return new TaxTypeResponse(savedTaxType);
    }

    // ENDPOINT 4: Excluir
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTaxType(
            @PathVariable Long id,
            @RequestHeader("X-User-Role") String role
    ) {
        // Validação de role
        if (!"ADMIN".equals(role)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Apenas ADMIN pode excluir impostos");
        }

        // Verifica se o imposto existe antes de excluir
        if (!taxTypeRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de imposto não encontrado");
        }

        taxTypeRepository.deleteById(id);
    }
}