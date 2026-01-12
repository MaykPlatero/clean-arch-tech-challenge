package br.com.fiap.clean_arch.domain.entities;

import br.com.fiap.clean_arch.domain.exceptions.DomainException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
public class Restaurant {
    private Long id;
    private String name;
    private String address;
    private String cuisineType;
    private Set<User> restaurantOwners;
    private Set<OpeningHours> openingHoursSet;

    public static Restaurant create(String name, String address, String cuisineType, Set<User> restaurantOwners,
                                    Set<OpeningHours> openingHoursList) {
        return Restaurant.create(null, name, address, cuisineType, restaurantOwners, openingHoursList);
    }

    public static Restaurant create(Long id, String name, String address, String cuisineType, Set<User> restaurantOwners,
                                    Set<OpeningHours> openingHoursList) {
        validateRestaurant(name, address, cuisineType, restaurantOwners, openingHoursList);

        Restaurant restaurant = new Restaurant();
        restaurant.setId(id);
        restaurant.setName(name);
        restaurant.setAddress(address);
        restaurant.setCuisineType(cuisineType);
        restaurant.setRestaurantOwners(restaurantOwners);
        restaurant.setOpeningHoursSet(openingHoursList);

        return restaurant;
    }

    private static void validateRestaurant(String name, String address, String cuisineType, Set<User> restaurantOwners, Set<OpeningHours> openingHoursList) {
        if (name == null || name.trim().isEmpty()) {
            throw new DomainException("Restaurant name is required");
        }
        if (name.length() < 3) {
            throw new DomainException("Restaurant name must have at least 3 characters");
        }
        if (address == null) {
            throw new DomainException("Address is required");
        }
        if (cuisineType == null || cuisineType.trim().isEmpty()) {
            throw new DomainException("Cuisine type is required");
        }
        // Validações comentadas pelo @JsonIgnore na entidade
//        if (restaurantOwners == null || restaurantOwners.isEmpty()) {
//            throw new DomainException("At least 1 restaurant owner is required");
//        }
//        if (openingHoursList == null || openingHoursList.isEmpty()) {
//            throw new DomainException("At least one opening hours entry is required");
//        }
    }
}
