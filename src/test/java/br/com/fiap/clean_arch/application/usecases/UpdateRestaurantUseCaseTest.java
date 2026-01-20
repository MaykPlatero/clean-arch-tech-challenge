package br.com.fiap.clean_arch.application.usecases;

import br.com.fiap.clean_arch.application.ports.RestaurantRepository;
import br.com.fiap.clean_arch.domain.entities.OpeningHours;
import br.com.fiap.clean_arch.domain.entities.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UpdateRestaurantUseCaseTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private UpdateRestaurantUseCase updateRestaurantUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldUpdateRestaurant() {
        Set<OpeningHours> oldHours = new HashSet<>();
        oldHours.add(new OpeningHours(1L, 1L, DayOfWeek.MONDAY, LocalTime.of(18, 0), LocalTime.of(23, 0)));
        
        Restaurant existingRestaurant = Restaurant.create(1L, "Old Name", "Old Address", 
            "Old Type", new HashSet<>(), oldHours);

        Set<OpeningHours> newHours = new HashSet<>();
        newHours.add(new OpeningHours(2L, 1L, DayOfWeek.TUESDAY, LocalTime.of(19, 0), LocalTime.of(22, 0)));
        
        Restaurant updatedRestaurant = Restaurant.create(1L, "New Name", "New Address", 
            "New Type", new HashSet<>(), newHours);

        when(restaurantRepository.findById(1L)).thenReturn(existingRestaurant);
        when(restaurantRepository.save(any(Restaurant.class))).thenReturn(updatedRestaurant);

        Restaurant result = updateRestaurantUseCase.execute(1L, "New Name", "New Address", 
            "New Type", newHours);

        assertNotNull(result);
        assertEquals("New Name", result.getName());
        verify(restaurantRepository, times(1)).findById(1L);
        verify(restaurantRepository, times(1)).save(any(Restaurant.class));
    }
}
