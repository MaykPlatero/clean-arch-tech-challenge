package br.com.fiap.clean_arch.presentation.mappers;

import br.com.fiap.clean_arch.domain.entities.Restaurant;
import br.com.fiap.clean_arch.presentation.dto.response.RestaurantResponse;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantMapperTest {

    @Test
    void shouldMapToResponse() {
        Restaurant restaurant = Restaurant.create(1L, "Pizza Place", "Rua Augusta, 123", 
            "Italian", new HashSet<>(), new HashSet<>());
        
        RestaurantResponse result = RestaurantMapper.toResponse(restaurant);
        
        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("Pizza Place", result.name());
        assertEquals("Rua Augusta, 123", result.address());
        assertEquals("Italian", result.cuisineType());
        assertNotNull(result.openingHours());
        assertNotNull(result.owners());
    }
}
