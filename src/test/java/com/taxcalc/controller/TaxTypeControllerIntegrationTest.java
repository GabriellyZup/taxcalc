// TaxTypeControllerIntegrationTest.java (novo)
package com.taxcalc.controller;

import com.taxcalc.dto.request.TaxTypeRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaxTypeControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private HttpHeaders createAdminHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-User-Role", "ADMIN");
        return headers;
    }

    @Test
    void createTaxType_AsAdmin_ShouldSucceed() {
        TaxTypeRequest request = new TaxTypeRequest(
                "IVA Test",
                BigDecimal.valueOf(23.0),
                "Test tax"
        );

        HttpEntity<TaxTypeRequest> entity = new HttpEntity<>(request, createAdminHeaders());

        ResponseEntity<?> response = restTemplate.postForEntity(
                "/tipos",
                entity,
                Void.class
        );

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}