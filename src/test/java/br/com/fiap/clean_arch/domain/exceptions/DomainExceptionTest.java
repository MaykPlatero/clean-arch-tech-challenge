package br.com.fiap.clean_arch.domain.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DomainExceptionTest {

    @Test
    void shouldCreateDomainExceptionWithMessage() {
        String message = "Test domain exception";
        
        DomainException exception = new DomainException(message);
        
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
    }
}
