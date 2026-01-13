package br.com.fiap.clean_arch.application.usecases;

import br.com.fiap.clean_arch.application.ports.RestaurantRepository;
import br.com.fiap.clean_arch.application.ports.UserRepository;
import br.com.fiap.clean_arch.domain.entities.OpeningHours;
import br.com.fiap.clean_arch.domain.entities.Restaurant;
import br.com.fiap.clean_arch.domain.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateRestaurantUseCaseTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private UserRepository userRepository;

    private CreateRestaurantUseCase createRestaurantUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        createRestaurantUseCase = new CreateRestaurantUseCase(restaurantRepository, userRepository);
    }

    @Test
    void shouldCreateRestaurantSuccessfully() {
        String name = "Pizza Place";
        String address = "Rua Augusta, 123";
        String cuisineType = "Italian";
        List<Long> ownerIds = List.of(1L);
        Set<OpeningHours> hours = Set.of(new OpeningHours(null, null, DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(22, 0)));
        
        User mockUser = mock(User.class);
        when(mockUser.getId()).thenReturn(1L);
        when(userRepository.findById(1L)).thenReturn(mockUser);
        
        Restaurant savedRestaurant = Restaurant.create(1L, name, address, cuisineType, Set.of(mockUser), hours);
        when(restaurantRepository.save(any(Restaurant.class))).thenReturn(savedRestaurant);
        
        Restaurant result = createRestaurantUseCase.execute(name, address, cuisineType, ownerIds, hours);
        
        assertNotNull(result);
        assertEquals(name, result.getName());
        verify(userRepository).findById(1L);
        verify(restaurantRepository).save(any(Restaurant.class));
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        String name = "Pizza Place";
        String address = "Rua Augusta, 123";
        String cuisineType = "Italian";
        List<Long> ownerIds = List.of(999L);
        Set<OpeningHours> hours = Set.of();
        
        when(userRepository.findById(999L)).thenReturn(null);
        
        assertThrows(IllegalArgumentException.class, () -> 
            createRestaurantUseCase.execute(name, address, cuisineType, ownerIds, hours));
        
        verify(userRepository).findById(999L);
        verify(restaurantRepository, never()).save(any());
    }
}
