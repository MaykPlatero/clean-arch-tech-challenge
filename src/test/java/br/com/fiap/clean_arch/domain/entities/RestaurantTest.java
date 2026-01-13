package br.com.fiap.clean_arch.domain.entities;

import br.com.fiap.clean_arch.domain.exceptions.DomainException;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {

    @Test
    void shouldCreateRestaurantSuccessfully() {
        Set<User> owners = new HashSet<>();
        Set<OpeningHours> hours = new HashSet<>();
        
        Restaurant restaurant = Restaurant.create("Pizza Place", "Rua Augusta, 123", "Italian", owners, hours);
        
        assertNotNull(restaurant);
        assertEquals("Pizza Place", restaurant.getName());
        assertEquals("Rua Augusta, 123", restaurant.getAddress());
        assertEquals("Italian", restaurant.getCuisineType());
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        Set<User> owners = new HashSet<>();
        Set<OpeningHours> hours = new HashSet<>();
        
        assertThrows(DomainException.class, () -> 
            Restaurant.create(null, "Rua Augusta, 123", "Italian", owners, hours));
    }

    @Test
    void shouldThrowExceptionWhenNameIsEmpty() {
        Set<User> owners = new HashSet<>();
        Set<OpeningHours> hours = new HashSet<>();
        
        assertThrows(DomainException.class, () -> 
            Restaurant.create("", "Rua Augusta, 123", "Italian", owners, hours));
    }

    @Test
    void shouldThrowExceptionWhenNameIsTooShort() {
        Set<User> owners = new HashSet<>();
        Set<OpeningHours> hours = new HashSet<>();
        
        assertThrows(DomainException.class, () -> 
            Restaurant.create("AB", "Rua Augusta, 123", "Italian", owners, hours));
    }
}
