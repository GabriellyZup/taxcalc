package com.taxcalc.controller;

import com.taxcalc.model.TaxType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaxTypeControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void createTaxType_AdminRole_ReturnsCreated() {
        TaxType taxType = new TaxType();
        taxType.setName("ICMS");
        taxType.setTaxRate(18.0);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-User-Role", "ADMIN"); // Simulação de role

        ResponseEntity<TaxType> response = restTemplate.exchange(
                "/tipos",
                HttpMethod.POST,
                new HttpEntity<>(taxType, headers),
                TaxType.class
        );

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody().getId());
    }
}