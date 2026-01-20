package br.com.fiap.clean_arch.presentation.dto.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateUserRequestTest {

    @Test
    void shouldCreateUpdateUserRequest() {
        UpdateUserRequest request = new UpdateUserRequest(
            "John Doe",
            "john@test.com",
            "12345678901",
            "Rua A, 123",
            "client",
            "johndoe",
            "password123"
        );

        assertNotNull(request);
        assertEquals("John Doe", request.name());
        assertEquals("john@test.com", request.email());
        assertEquals("12345678901", request.userIdentification());
        assertEquals("Rua A, 123", request.address());
        assertEquals("client", request.profile());
        assertEquals("johndoe", request.username());
        assertEquals("password123", request.password());
    }
}
