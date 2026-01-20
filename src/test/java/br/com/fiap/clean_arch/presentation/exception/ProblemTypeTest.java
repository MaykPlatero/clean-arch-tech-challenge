package br.com.fiap.clean_arch.presentation.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProblemTypeTest {

    @Test
    void shouldHaveBusinessError() {
        ProblemType type = ProblemType.BUSINESS_ERROR;
        
        assertNotNull(type);
        assertEquals("Business error", type.getTitle());
        assertNotNull(type.getUri());
    }

    @Test
    void shouldHaveResourceNotFound() {
        ProblemType type = ProblemType.RESOUCE_NOT_FOUND;
        
        assertNotNull(type);
        assertEquals("Resource not found", type.getTitle());
        assertNotNull(type.getUri());
    }

    @Test
    void shouldHaveValidationError() {
        ProblemType type = ProblemType.VALIDATION_ERROR;
        
        assertNotNull(type);
        assertEquals("Validation error", type.getTitle());
        assertNotNull(type.getUri());
    }

    @Test
    void shouldHaveInternalError() {
        ProblemType type = ProblemType.INTERNAL_ERROR;
        
        assertNotNull(type);
        assertEquals("Internal error", type.getTitle());
        assertNotNull(type.getUri());
    }
}
