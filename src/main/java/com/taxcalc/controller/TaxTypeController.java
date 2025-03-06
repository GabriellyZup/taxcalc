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

    @GetMapping
    public List<TaxType> getAllTaxTypes() {
        return taxTypeRepository.findAll();
    }

    @GetMapping("/{id}")
    public TaxType getTaxTypeById(@PathVariable Long id) {
        return taxTypeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tax type not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaxType createTaxType(@RequestBody TaxType taxType) {
        if (taxType.getTaxRate() < 0 || taxType.getTaxRate() > 100) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tax rate must be between 0 and 100");
        }
        return taxTypeRepository.save(taxType);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTaxType(@PathVariable Long id) {
        taxTypeRepository.deleteById(id);
    }
}