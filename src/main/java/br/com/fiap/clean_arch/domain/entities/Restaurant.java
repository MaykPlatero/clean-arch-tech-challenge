package br.com.fiap.clean_arch.domain.entities;

import br.com.fiap.clean_arch.domain.exceptions.DomainException;
import lombok.Getter;

import java.util.List;

@Getter
public class Restaurant {
    private Long id;
    private String name;
    private Address address;
    private String cuisineType;
    private User restaurantOwner;
    private List<OpeningHours> openingHoursList;

    public Restaurant(String name, Address address, String cuisineType, User restaurantOwner, List<OpeningHours> openingHoursList) {
        validateRestaurant(name, address, cuisineType, restaurantOwner, openingHoursList);
        this.name = name;
        this.address = address;
        this.cuisineType = cuisineType;
        this.restaurantOwner = restaurantOwner;
        this.openingHoursList = openingHoursList;
    }

    public Restaurant(Long id, String name, Address address, String cuisineType, User restaurantOwner,
                      List<OpeningHours> openingHoursList) {
        this(name, address, cuisineType, restaurantOwner, openingHoursList);
        this.id = id;
    }

    private static void validateRestaurant(String name, Address address, String cuisineType, User restaurantOwner, List<OpeningHours> openingHoursList) {
        if (name == null || name.trim().isEmpty()) {
            throw new DomainException("Restaurant name is required");
        }
        if (name.length() < 3) {
            throw new DomainException("Restaurant name must have at least 3 characters");
        }
        if (address == null || address.street.isEmpty() || address.city.isEmpty()) {
            throw new DomainException("Address street and city are required");
        }
        if (cuisineType == null || cuisineType.trim().isEmpty()) {
            throw new DomainException("Cuisine type is required");
        }
        if (restaurantOwner == null || restaurantOwner.getId() == null) {
            throw new DomainException("Restaurant owner is required");
        }
        if (openingHoursList == null || openingHoursList.isEmpty()) {
            throw new DomainException("At least one opening hours entry is required");
        }
    }
}
