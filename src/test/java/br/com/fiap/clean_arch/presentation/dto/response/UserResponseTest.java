package br.com.fiap.clean_arch.presentation.dto.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserResponseTest {

    @Test
    void shouldCreateUserResponse() {
        UserResponse response = new UserResponse(
            1L,
            "John Doe",
            "john@test.com",
            "12345678901",
            "Rua A, 123",
            "CLIENT"
        );

        assertNotNull(response);
        assertEquals(1L, response.id());
        assertEquals("John Doe", response.name());
        assertEquals("john@test.com", response.email());
        assertEquals("12345678901", response.userIdentification());
        assertEquals("Rua A, 123", response.address());
        assertEquals("CLIENT", response.profile());
    }
}
