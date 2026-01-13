package br.com.fiap.clean_arch.domain.entities;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MenuItemTest {

    @Test
    void shouldCreateMenuItemSuccessfully() {
        MenuItem menuItem = MenuItem.create(1L, "Pizza Margherita", "Traditional pizza", 
            new BigDecimal("25.90"), true, "https://example.com/pizza.jpg", ZonedDateTime.now());
        
        assertNotNull(menuItem);
        assertEquals("Pizza Margherita", menuItem.getName());
        assertEquals("Traditional pizza", menuItem.getDescription());
        assertEquals(new BigDecimal("25.90"), menuItem.getPrice());
        assertEquals(1L, menuItem.getRestaurantId());
        assertTrue(menuItem.isDeliveryItem());
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> 
            MenuItem.create(1L, null, "Description", new BigDecimal("25.90"), 
                true, "https://example.com/pizza.jpg", ZonedDateTime.now()));
    }

    @Test
    void shouldThrowExceptionWhenPriceIsZero() {
        assertThrows(IllegalArgumentException.class, () -> 
            MenuItem.create(1L, "Pizza", "Description", BigDecimal.ZERO, 
                true, "https://example.com/pizza.jpg", ZonedDateTime.now()));
    }
}
