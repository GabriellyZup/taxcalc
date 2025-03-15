//package com.taxcalc.service;
//
//import com.taxcalc.dto.CalculationResponseDTO;
//import com.taxcalc.exception.ResourceNotFoundException;
//import com.taxcalc.model.TaxType;
//import com.taxcalc.repository.TaxTypeRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvSource;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.math.BigDecimal;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class TaxCalculationServiceTest {
//
//    @Mock
//    private TaxTypeRepository taxTypeRepository;
//
//    @InjectMocks
//    private TaxCalculationService taxCalculationService;
//
//    // Testes Positivos
//    @ParameterizedTest
//    @CsvSource({
//            "1000.00, 18.0, 180.00",   // Caso normal
//            "0.00, 10.0, 0.00",        // Valor zero
//            "1234.56, 7.5, 92.59"      // Valores decimais
//    })
//    void calculateTax_ShouldReturnCorrectValue(String baseValue, String taxRate, String expected) {
//        // Arrange
//        TaxType taxType = new TaxType(1L, "Test Tax", "Desc", new BigDecimal(taxRate));
//        when(taxTypeRepository.findById(1L)).thenReturn(Optional.of(taxType));
//
//        // Act
//        BigDecimal result = taxCalculationService.calculateTax(1L, new BigDecimal(baseValue));
//
//        // Assert
//        assertEquals(new BigDecimal(expected), result);
//        verify(taxTypeRepository).findById(1L);
//    }
//
//    @Test
//    void calculateTax_ShouldMatchJsonResponseStructure() {
//        TaxType taxType = new TaxType();
//        taxType.setNome("ICMS");
//        taxType.setAliquota(new BigDecimal("18.00"));
//
//        when(taxTypeRepository.findById(1L)).thenReturn(Optional.of(taxType));
//
//        CalculationResponseDTO response = service.buildCalculationResponse(
//                1L,
//                new BigDecimal("1000.00"),
//                new BigDecimal("180.00")
//        );
//
//        assertAll(
//                () -> assertEquals("ICMS", response.getTipoImposto()),
//                () -> assertEquals(new BigDecimal("1000.00"), response.getValorBase()),
//                () -> assertEquals(new BigDecimal("18.00"), response.getAliquota()),
//                () -> assertEquals(new BigDecimal("180.00"), response.getValorImposto())
//        );
//
//    }
//
//    // Testes Negativos
//    @Test
//    void calculateTax_ShouldThrowWhenTaxTypeNotFound() {
//        // Arrange
//        when(taxTypeRepository.findById(anyLong())).thenReturn(Optional.empty());
//
//        // Act & Assert
//        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
//                () -> taxCalculationService.calculateTax(99L, BigDecimal.TEN));
//
//        assertEquals("Imposto não localizado", exception.getMessage());
//    }
//
//    @Test
//    void calculateTax_ShouldThrowWhenNegativeBaseValue() {
//        // Arrange
//        TaxType taxType = new TaxType(1L, "Tax", "Desc", BigDecimal.TEN);
//        when(taxTypeRepository.findById(1L)).thenReturn(Optional.of(taxType));
//
//        // Act & Assert
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
//                () -> taxCalculationService.calculateTax(1L, new BigDecimal("-100")));
//
//        assertEquals("Valor base não pode ser negativo", exception.getMessage());
//    }
//}