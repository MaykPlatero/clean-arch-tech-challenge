package br.com.fiap.clean_arch.domain.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourceNotFoundExceptionTest {

    @Test
    void shouldCreateExceptionWithMessage() {
        br.com.fiap.clean_arch.domain.exceptions.ResouceNotFoundException exception = 
            new br.com.fiap.clean_arch.domain.exceptions.ResouceNotFoundException("Resource not found");
        
        assertNotNull(exception);
        assertEquals("Resource not found", exception.getMessage());
    }
}
