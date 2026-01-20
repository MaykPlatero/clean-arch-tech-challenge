package br.com.fiap.clean_arch.presentation.dto.response;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MenuItemResponseTest {

    @Test
    void shouldCreateMenuItemResponse() {
        ZonedDateTime now = ZonedDateTime.now();
        MenuItemResponse response = new MenuItemResponse(
            1L,
            1L,
            "Pizza Margherita",
            "Pizza tradicional",
            BigDecimal.valueOf(42.90),
            true,
            "http://photo.jpg",
            now
        );

        assertNotNull(response);
        assertEquals(1L, response.id());
        assertEquals(1L, response.restaurantId());
        assertEquals("Pizza Margherita", response.name());
        assertEquals("Pizza tradicional", response.description());
        assertEquals(BigDecimal.valueOf(42.90), response.price());
        assertTrue(response.deliveryItem());
        assertEquals("http://photo.jpg", response.photoUrl());
        assertEquals(now, response.lastUpdate());
    }
}
