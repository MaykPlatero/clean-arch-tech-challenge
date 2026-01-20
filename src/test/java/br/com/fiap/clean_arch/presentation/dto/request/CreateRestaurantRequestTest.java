package br.com.fiap.clean_arch.presentation.dto.request;

import br.com.fiap.clean_arch.presentation.dto.OpeningHoursDTO;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CreateRestaurantRequestTest {

    @Test
    void shouldCreateRestaurantRequest() {
        OpeningHoursDTO hours = new OpeningHoursDTO("MONDAY", "18:00", "23:00");
        Set<Long> userIds = new HashSet<>();
        userIds.add(1L);
        userIds.add(2L);
        
        Set<OpeningHoursDTO> openingHours = new HashSet<>();
        openingHours.add(hours);

        CreateRestaurantRequest request = new CreateRestaurantRequest(
            "Pizzaria Bella",
            "Rua A, 123",
            "Italiana",
            userIds,
            openingHours
        );

        assertNotNull(request);
        assertEquals("Pizzaria Bella", request.name());
        assertEquals("Rua A, 123", request.address());
        assertEquals("Italiana", request.cuisineType());
        assertEquals(2, request.userIds().size());
        assertEquals(1, request.openingHours().size());
    }
}
