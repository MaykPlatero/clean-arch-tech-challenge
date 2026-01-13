package br.com.fiap.clean_arch.domain.entities;

import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserCredentialsTest {

    @Test
    void shouldCreateUserCredentialsWithAllFields() {
        Long id = 1L;
        String username = "testuser";
        String password = "password123";
        ZonedDateTime lastUpdate = ZonedDateTime.now();
        
        UserCredentials credentials = UserCredentials.create(id, username, password, lastUpdate);
        
        assertNotNull(credentials);
        assertEquals(id, credentials.getId());
        assertEquals(username, credentials.getUsername());
        assertEquals(password, credentials.getPassword());
        assertEquals(lastUpdate, credentials.getLastUpdate());
    }

    @Test
    void shouldCreateUserCredentialsWithoutId() {
        String username = "testuser";
        String password = "password123";
        ZonedDateTime lastUpdate = ZonedDateTime.now();
        
        UserCredentials credentials = UserCredentials.create(username, password, lastUpdate);
        
        assertNotNull(credentials);
        assertNull(credentials.getId());
        assertEquals(username, credentials.getUsername());
        assertEquals(password, credentials.getPassword());
        assertEquals(lastUpdate, credentials.getLastUpdate());
    }

    @Test
    void shouldThrowExceptionWhenUsernameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> 
            UserCredentials.create(null, "password", ZonedDateTime.now()));
    }

    @Test
    void shouldThrowExceptionWhenPasswordIsNull() {
        assertThrows(IllegalArgumentException.class, () -> 
            UserCredentials.create("username", null, ZonedDateTime.now()));
    }
}
