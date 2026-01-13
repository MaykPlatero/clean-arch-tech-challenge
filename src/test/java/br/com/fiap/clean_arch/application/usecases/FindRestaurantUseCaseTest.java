package br.com.fiap.clean_arch.application.usecases;

import br.com.fiap.clean_arch.application.ports.RestaurantRepository;
import br.com.fiap.clean_arch.domain.entities.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FindRestaurantUseCaseTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    private FindRestaurantUseCase findRestaurantUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        findRestaurantUseCase = new FindRestaurantUseCase(restaurantRepository);
    }

    @Test
    void shouldFindRestaurantById() {
        Long restaurantId = 1L;
        Restaurant expectedRestaurant = Restaurant.create(restaurantId, "Pizza Place", 
            "Rua Augusta, 123", "Italian", new HashSet<>(), new HashSet<>());
        
        when(restaurantRepository.findById(restaurantId)).thenReturn(expectedRestaurant);
        
        Restaurant result = findRestaurantUseCase.execute(restaurantId);
        
        assertNotNull(result);
        assertEquals(restaurantId, result.getId());
        assertEquals("Pizza Place", result.getName());
        verify(restaurantRepository).findById(restaurantId);
    }
}
