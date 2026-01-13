package br.com.fiap.clean_arch.presentation.mappers;

import br.com.fiap.clean_arch.domain.entities.MenuItem;
import br.com.fiap.clean_arch.presentation.dto.response.MenuItemResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MenuItemMapperTest {

    @Test
    void shouldMapMenuItemToResponse() {
        MenuItem menuItem = MenuItem.create(
            1L,
            10L,
            "Pizza Margherita",
            "Delicious pizza",
            BigDecimal.valueOf(25.99),
            true,
            "http://example.com/pizza.jpg"
        );
        
        MenuItemResponse response = MenuItemMapper.toResponse(menuItem);
        
        assertNotNull(response);
        assertEquals(1L, response.id());
        assertEquals("Pizza Margherita", response.name());
        assertEquals("Delicious pizza", response.description());
        assertEquals(BigDecimal.valueOf(25.99), response.price());
        assertEquals("http://example.com/pizza.jpg", response.photoUrl());
        assertEquals(10L, response.restaurantId());
    }
}
