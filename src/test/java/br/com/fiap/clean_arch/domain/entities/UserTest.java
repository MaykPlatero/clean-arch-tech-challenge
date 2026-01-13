package br.com.fiap.clean_arch.domain.entities;

import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void shouldCreateUserWithAllFields() {
        UserCredentials credentials = UserCredentials.create(1L, "testuser", "password", ZonedDateTime.now());
        
        User user = User.create(1L, "John Doe", "123456789", "john@test.com", 
            "Rua A, 123", credentials, EProfile.client, ZonedDateTime.now());
        
        assertNotNull(user);
        assertEquals(1L, user.getId());
        assertEquals("John Doe", user.getName());
        assertEquals("123456789", user.getUserIdentification());
        assertEquals("john@test.com", user.getEmail());
        assertEquals("Rua A, 123", user.getAddress());
        assertEquals(EProfile.client, user.getProfile());
        assertNotNull(user.getUserCredentials());
    }

    @Test
    void shouldCreateUserWithoutId() {
        UserCredentials credentials = UserCredentials.create("testuser", "password", ZonedDateTime.now());
        
        User user = User.create("John Doe", "123456789", "john@test.com", 
            "Rua A, 123", credentials, EProfile.client, ZonedDateTime.now());
        
        assertNotNull(user);
        assertNull(user.getId());
        assertEquals("John Doe", user.getName());
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        UserCredentials credentials = UserCredentials.create("testuser", "password", ZonedDateTime.now());
        
        assertThrows(IllegalArgumentException.class, () -> 
            User.create(null, "123456789", "john@test.com", "Address", credentials, EProfile.client, ZonedDateTime.now()));
    }

    @Test
    void shouldThrowExceptionWhenEmailIsNull() {
        UserCredentials credentials = UserCredentials.create("testuser", "password", ZonedDateTime.now());
        
        assertThrows(IllegalArgumentException.class, () -> 
            User.create("John", "123456789", null, "Address", credentials, EProfile.client, ZonedDateTime.now()));
    }
}
