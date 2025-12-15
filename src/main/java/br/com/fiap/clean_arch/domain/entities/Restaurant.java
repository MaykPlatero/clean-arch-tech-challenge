package br.com.fiap.clean_arch.domain.entities;

import br.com.fiap.clean_arch.domain.exceptions.DomainException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
public class Restaurant {
    private Long id;
    private String name;
    private Address address;
    private String cuisineType;
    private User restaurantOwner;
    private List<OpeningHours> openingHoursList;

    public static Restaurant create(String name, Address address, String cuisineType, User restaurantOwner,
                                    List<OpeningHours> openingHoursList) {
        validateRestaurant(name, address, cuisineType, restaurantOwner, openingHoursList);

        Restaurant restaurant = new Restaurant();
        restaurant.setName(name);
        restaurant.setAddress(address);
        restaurant.setCuisineType(cuisineType);
        restaurant.setRestaurantOwner(restaurantOwner);
        restaurant.setOpeningHoursList(openingHoursList);

        return restaurant;
    }

    public static Restaurant create(Long id, String name, Address address, String cuisineType, User restaurantOwner,
                      List<OpeningHours> openingHoursList) {
        Restaurant restaurant = create(name, address, cuisineType, restaurantOwner, openingHoursList);
        restaurant.setId(id);
        return restaurant;
    }

    private static void validateRestaurant(String name, Address address, String cuisineType, User restaurantOwner, List<OpeningHours> openingHoursList) {
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
        if (restaurantOwner == null) {
            throw new DomainException("Restaurant owner is required");
        }
        if (openingHoursList == null || openingHoursList.isEmpty()) {
            throw new DomainException("At least one opening hours entry is required");
        }
    }
}
