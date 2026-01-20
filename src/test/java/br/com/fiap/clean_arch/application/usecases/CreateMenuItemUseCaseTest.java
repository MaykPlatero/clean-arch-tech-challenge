package br.com.fiap.clean_arch.application.usecases;

import br.com.fiap.clean_arch.application.ports.MenuItemRepository;
import br.com.fiap.clean_arch.application.ports.RestaurantRepository;
import br.com.fiap.clean_arch.domain.entities.MenuItem;
import br.com.fiap.clean_arch.domain.entities.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateMenuItemUseCaseTest {

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private CreateMenuItemUseCase createMenuItemUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateMenuItem() {
        Restaurant restaurant = Restaurant.create(1L, "Test Restaurant", "Address", 
            "Type", new HashSet<>(), new HashSet<>());
        MenuItem menuItem = MenuItem.create(1L, 1L, "Pizza", "Delicious", 
            BigDecimal.valueOf(30.0), true, "http://photo.jpg");

        when(restaurantRepository.findById(1L)).thenReturn(restaurant);
        when(menuItemRepository.save(any(MenuItem.class))).thenReturn(menuItem);

        MenuItem result = createMenuItemUseCase.execute(1L, "Pizza", "Delicious", 
            BigDecimal.valueOf(30.0), true, "http://photo.jpg");

        assertNotNull(result);
        assertEquals("Pizza", result.getName());
        verify(menuItemRepository, times(1)).save(any(MenuItem.class));
    }
}
