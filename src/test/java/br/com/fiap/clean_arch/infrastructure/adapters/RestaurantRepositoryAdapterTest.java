package br.com.fiap.clean_arch.infrastructure.adapters;

import br.com.fiap.clean_arch.domain.entities.OpeningHours;
import br.com.fiap.clean_arch.domain.entities.Restaurant;
import br.com.fiap.clean_arch.domain.entities.User;
import br.com.fiap.clean_arch.infrastructure.persistence.entity.*;
import br.com.fiap.clean_arch.infrastructure.persistence.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RestaurantRepositoryAdapterTest {

    @Mock
    private RestaurantJpaRepository restaurantJpaRepository;
    @Mock
    private UserRestaurantJpaRepository userRestaurantJpaRepository;
    @Mock
    private OpeningHoursJpaRepository openingHoursJpaRepository;
    @Mock
    private UserJpaRepository userJpaRepository;

    private RestaurantRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        adapter = new RestaurantRepositoryAdapter(restaurantJpaRepository, userRestaurantJpaRepository, 
            openingHoursJpaRepository, userJpaRepository);
    }

    @Test
    void shouldFindRestaurantById() {
        Long restaurantId = 1L;
        
        RestaurantEntity restaurantEntity = new RestaurantEntity();
        restaurantEntity.setId(restaurantId);
        restaurantEntity.setName("Pizza Place");
        restaurantEntity.setAddress("Rua Augusta, 123");
        restaurantEntity.setCuisineType("Italian");
        
        UserCredentialsEntity credentialsEntity = new UserCredentialsEntity();
        credentialsEntity.setId(1L);
        credentialsEntity.setUsername("john");
        credentialsEntity.setPassword("password");
        
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setName("John Doe");
        userEntity.setUserIdentification("123456789");
        userEntity.setEmail("john@example.com");
        userEntity.setAddress("Rua A, 123");
        userEntity.setUserCredentials(credentialsEntity);
        userEntity.setProfile(br.com.fiap.clean_arch.domain.entities.EProfile.client);
        
        UserRestaurantEntity userRestaurantEntity = new UserRestaurantEntity();
        userRestaurantEntity.setUserId(1L);
        userRestaurantEntity.setRestaurantId(restaurantId);
        
        OpeningHoursEntity openingHoursEntity = new OpeningHoursEntity();
        openingHoursEntity.setId(1L);
        openingHoursEntity.setRestaurantId(restaurantId);
        openingHoursEntity.setDayOfWeek(DayOfWeek.MONDAY);
        openingHoursEntity.setOpenTime(LocalTime.of(10, 0));
        openingHoursEntity.setCloseTime(LocalTime.of(22, 0));
        
        when(restaurantJpaRepository.findById(restaurantId)).thenReturn(Optional.of(restaurantEntity));
        when(userRestaurantJpaRepository.findByRestaurantId(restaurantId)).thenReturn(List.of(userRestaurantEntity));
        when(userJpaRepository.findById(1L)).thenReturn(Optional.of(userEntity));
        when(openingHoursJpaRepository.findByRestaurantId(restaurantId)).thenReturn(List.of(openingHoursEntity));
        
        Restaurant result = adapter.findById(restaurantId);
        
        assertNotNull(result);
        assertEquals(restaurantId, result.getId());
        assertEquals("Pizza Place", result.getName());
        verify(restaurantJpaRepository).findById(restaurantId);
    }

    @Test
    void shouldSaveRestaurant() {
        Restaurant restaurant = Restaurant.create("Pizza Place", "Rua Augusta, 123", "Italian", 
            Set.of(), Set.of());
        
        RestaurantEntity savedEntity = new RestaurantEntity();
        savedEntity.setId(1L);
        savedEntity.setName("Pizza Place");
        savedEntity.setAddress("Rua Augusta, 123");
        savedEntity.setCuisineType("Italian");
        
        when(restaurantJpaRepository.save(any(RestaurantEntity.class))).thenReturn(savedEntity);
        when(restaurantJpaRepository.findById(1L)).thenReturn(Optional.of(savedEntity));
        when(userRestaurantJpaRepository.findByRestaurantId(1L)).thenReturn(List.of());
        when(openingHoursJpaRepository.findByRestaurantId(1L)).thenReturn(List.of());
        
        Restaurant result = adapter.save(restaurant);
        
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(restaurantJpaRepository).save(any(RestaurantEntity.class));
    }
}
