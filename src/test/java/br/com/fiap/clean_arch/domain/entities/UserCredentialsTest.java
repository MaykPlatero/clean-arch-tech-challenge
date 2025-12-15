package br.com.fiap.clean_arch.domain.entities;

import org.junit.jupiter.api.Test;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserCredentialsTest {
    @Test
    void createUserCredentials_withValidData_shouldSucceed() {
        UserCredentials credentials = UserCredentials.create("user", "password123", ZonedDateTime.now());
        assertNotNull(credentials);
        assertEquals("user", credentials.getUsername());
    }

    @Test
    void createUserCredentials_withShortPassword_shouldThrow() {
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
            UserCredentials.create("user", "123", ZonedDateTime.now())
        );
        assertTrue(ex.getMessage().contains("at least 6 characters"));
    }

    @Test
    void createUserCredentials_withEmptyUsername_shouldThrow() {
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
            UserCredentials.create("", "password123", ZonedDateTime.now())
        );
        assertTrue(ex.getMessage().contains("Username is required"));
    }

    @Test
    void createUserCredentials_withId_shouldSucceed() {
        UserCredentials credentials = UserCredentials.create(1L, "user", "password123", ZonedDateTime.now());
        assertEquals(1L, credentials.getId());
    }
}

