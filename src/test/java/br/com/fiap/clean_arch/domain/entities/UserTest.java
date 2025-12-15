package br.com.fiap.clean_arch.domain.entities;

import org.junit.jupiter.api.Test;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void createUser_withValidData_shouldSucceed() {
        Address address = Address.create("Main St", 123, "Apt 1", "Downtown", "City", "State", "Country", "12345-678", ZonedDateTime.now());
        UserCredentials credentials = UserCredentials.create("user", "password123", ZonedDateTime.now());
        User user = User.create("John Doe", "12345678900", "john@example.com", address, credentials, EProfile.client, ZonedDateTime.now());
        assertNotNull(user);
        assertEquals("John Doe", user.getName());
    }

    @Test
    void createUser_withEmptyName_shouldThrow() {
        Address address = Address.create("Main St", 123, "Apt 1", "Downtown", "City", "State", "Country", "12345-678", ZonedDateTime.now());
        UserCredentials credentials = UserCredentials.create("user", "password123", ZonedDateTime.now());
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
            User.create("", "12345678900", "john@example.com", address, credentials, EProfile.client, ZonedDateTime.now())
        );
        assertTrue(ex.getMessage().contains("Name is required"));
    }

    @Test
    void createUser_withNullAddress_shouldThrow() {
        UserCredentials credentials = UserCredentials.create("user", "password123", ZonedDateTime.now());
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
            User.create("John Doe", "12345678900", "john@example.com", null, credentials, EProfile.client, ZonedDateTime.now())
        );
        assertTrue(ex.getMessage().contains("Address is required"));
    }

    @Test
    void createUser_withId_shouldSucceed() {
        Address address = Address.create("Main St", 123, "Apt 1", "Downtown", "City", "State", "Country", "12345-678", ZonedDateTime.now());
        UserCredentials credentials = UserCredentials.create("user", "password123", ZonedDateTime.now());
        User user = User.create(1L, "John Doe", "12345678900", "john@example.com", address, credentials, EProfile.client, ZonedDateTime.now());
        assertEquals(1L, user.getId());
    }
}

