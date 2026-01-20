package br.com.fiap.clean_arch.application.usecases;

import br.com.fiap.clean_arch.application.ports.MenuItemRepository;
import br.com.fiap.clean_arch.domain.entities.MenuItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FindMenuItemsByRestaurantUseCaseTest {

    @Mock
    private MenuItemRepository menuItemRepository;

    @InjectMocks
    private FindMenuItemsByRestaurantUseCase findMenuItemsByRestaurantUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindMenuItemsByRestaurantId() {
        Long restaurantId = 1L;
        MenuItem item1 = MenuItem.create(1L, restaurantId, "Pizza", "Delicious", 
            BigDecimal.valueOf(30.0), true, "http://photo1.jpg");
        MenuItem item2 = MenuItem.create(2L, restaurantId, "Burger", "Tasty", 
            BigDecimal.valueOf(25.0), false, "http://photo2.jpg");

        when(menuItemRepository.findByRestaurantId(restaurantId))
            .thenReturn(Arrays.asList(item1, item2));

        List<MenuItem> result = findMenuItemsByRestaurantUseCase.execute(restaurantId);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(menuItemRepository, times(1)).findByRestaurantId(restaurantId);
    }
}
