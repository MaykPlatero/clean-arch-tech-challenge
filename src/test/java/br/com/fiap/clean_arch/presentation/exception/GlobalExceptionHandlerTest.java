package br.com.fiap.clean_arch.presentation.exception;

import br.com.fiap.clean_arch.domain.exceptions.BusinessRuleViolationException;
import br.com.fiap.clean_arch.domain.exceptions.ResouceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;

    @Mock
    private HttpServletRequest request;

    @Mock
    private MethodArgumentNotValidException validationException;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        handler = new GlobalExceptionHandler();
        when(request.getRequestURI()).thenReturn("/api/test");
    }

    @Test
    void shouldHandleBusinessRuleViolationException() {
        BusinessRuleViolationException ex = new BusinessRuleViolationException("Business rule violated");

        ProblemDetail result = handler.handleBusiness(ex, request);

        assertNotNull(result);
        assertEquals(HttpStatus.BAD_REQUEST.value(), result.getStatus());
        assertEquals("Business rule violated", result.getDetail());
    }

    @Test
    void shouldHandleResourceNotFoundException() {
        ResouceNotFoundException ex = new ResouceNotFoundException("Resource not found");

        ProblemDetail result = handler.handleNotFound(ex, request);

        assertNotNull(result);
        assertEquals(HttpStatus.NOT_FOUND.value(), result.getStatus());
        assertEquals("Resource not found", result.getDetail());
    }

    @Test
    void shouldHandleValidationException() {
        FieldError fieldError = new FieldError("object", "field", "error message");
        when(validationException.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getFieldErrors()).thenReturn(Arrays.asList(fieldError));

        ProblemDetail result = handler.handleValidation(validationException, request);

        assertNotNull(result);
        assertEquals(HttpStatus.BAD_REQUEST.value(), result.getStatus());
        assertEquals("one or more invalid fields", result.getDetail());
    }

    @Test
    void shouldHandleGenericException() {
        Exception ex = new Exception("Unexpected error");

        ProblemDetail result = handler.handleGeneric(ex, request);

        assertNotNull(result);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), result.getStatus());
        assertEquals("Erro inesperado. Contate o suporte.", result.getDetail());
    }
}
