package br.com.fiap.clean_arch.domain.entities;

import org.junit.jupiter.api.Test;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {
    @Test
    void createAddress_withValidData_shouldSucceed() {
        Address address = Address.create("Main St", 123, "Apt 1", "Downtown", "City", "State", "Country", "12345-678", ZonedDateTime.now());
        assertNotNull(address);
        assertEquals("Main St", address.getStreet());
        assertEquals(123, address.getNumber());
    }

    @Test
    void createAddress_withInvalidStreet_shouldThrow() {
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                Address.create("", 123, "Apt 1", "Downtown", "City", "State", "Country", "12345-678", ZonedDateTime.now())
        );
        assertTrue(ex.getMessage().contains("Street is required"));
    }

    @Test
    void createAddress_withInvalidNumber_shouldThrow() {
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                Address.create("Main St", 0, "Apt 1", "Downtown", "City", "State", "Country", "12345-678", ZonedDateTime.now())
        );
        assertTrue(ex.getMessage().contains("Number must be positive"));
    }

    @Test
    void createAddress_withId_shouldSucceed() {
        Address address = Address.create(1L, "Main St", 123, "Apt 1", "Downtown", "City", "State", "Country", "12345-678", ZonedDateTime.now());
        assertEquals(1L, address.getId());
    }
}