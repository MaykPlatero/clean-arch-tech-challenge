package br.com.fiap.clean_arch.domain.entities;

import br.com.fiap.clean_arch.domain.exceptions.DomainException;
import org.junit.jupiter.api.Test;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    @Test
    void createRestaurant_withValidData_shouldSucceed() {
        Address address = Address.create("Main St", 123, "Apt 1", "Downtown", "City", "State", "Country", "12345-678", ZonedDateTime.now());
        UserCredentials credentials = UserCredentials.create("user", "password123", ZonedDateTime.now());
        User owner = User.create("Owner", "12345678900", "owner@example.com", address, credentials, EProfile.owner, ZonedDateTime.now());
        OpeningHours hours = new OpeningHours(DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(18, 0));
        Restaurant restaurant = Restaurant.create("My Restaurant", address, "Italian", Set.of(owner), List.of(hours));
        assertNotNull(restaurant);
        assertEquals("My Restaurant", restaurant.getName());
    }

    @Test
    void createRestaurant_withEmptyName_shouldThrow() {
        Address address = Address.create("Main St", 123, "Apt 1", "Downtown", "City", "State", "Country", "12345-678", ZonedDateTime.now());
        UserCredentials credentials = UserCredentials.create("user", "password123", ZonedDateTime.now());
        User owner = User.create("Owner", "12345678900", "owner@example.com", address, credentials, EProfile.owner, ZonedDateTime.now());
        OpeningHours hours = new OpeningHours(DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(18, 0));
        Exception ex = assertThrows(DomainException.class, () ->
            Restaurant.create("", address, "Italian", Set.of(owner), List.of(hours))
        );
        assertTrue(ex.getMessage().contains("Restaurant name is required"));
    }

    @Test
    void createRestaurant_withNullOwner_shouldThrow() {
        Address address = Address.create("Main St", 123, "Apt 1", "Downtown", "City", "State", "Country", "12345-678", ZonedDateTime.now());
        OpeningHours hours = new OpeningHours(DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(18, 0));
        Exception ex = assertThrows(DomainException.class, () ->
            Restaurant.create("My Restaurant", address, "Italian", null, List.of(hours))
        );
        assertTrue(ex.getMessage().contains("Restaurant owner is required"));
    }

    @Test
    void createRestaurant_withId_shouldSucceed() {
        Address address = Address.create("Main St", 123, "Apt 1", "Downtown", "City", "State", "Country", "12345-678", ZonedDateTime.now());
        UserCredentials credentials = UserCredentials.create("user", "password123", ZonedDateTime.now());
        User owner = User.create("Owner", "12345678900", "owner@example.com", address, credentials, EProfile.owner, ZonedDateTime.now());
        OpeningHours hours = new OpeningHours(DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(18, 0));
        Restaurant restaurant = Restaurant.create(1L, "My Restaurant", address, "Italian", Set.of(owner), List.of(hours));
        assertEquals(1L, restaurant.getId());
    }
}

