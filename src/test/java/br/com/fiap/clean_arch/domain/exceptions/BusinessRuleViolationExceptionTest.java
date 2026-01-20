package br.com.fiap.clean_arch.domain.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusinessRuleViolationExceptionTest {

    @Test
    void shouldCreateExceptionWithMessage() {
        BusinessRuleViolationException exception = 
            new BusinessRuleViolationException("Business rule violated");
        
        assertNotNull(exception);
        assertEquals("Business rule violated", exception.getMessage());
    }
}
