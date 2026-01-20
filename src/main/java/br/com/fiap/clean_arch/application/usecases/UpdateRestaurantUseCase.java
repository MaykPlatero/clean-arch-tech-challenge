package br.com.fiap.clean_arch.application.usecases;

import br.com.fiap.clean_arch.application.ports.RestaurantRepository;
import br.com.fiap.clean_arch.domain.entities.OpeningHours;
import br.com.fiap.clean_arch.domain.entities.Restaurant;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UpdateRestaurantUseCase {
    private final RestaurantRepository restaurantRepository;

    public UpdateRestaurantUseCase(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant execute(Long id, String name, String address, String cuisineType, 
                             Set<OpeningHours> openingHoursSet) {
        Restaurant existingRestaurant = restaurantRepository.findById(id);
        if (existingRestaurant == null) {
            throw new RuntimeException("Restaurant not found with id: " + id);
        }

        Restaurant updatedRestaurant = Restaurant.create(
            id,
            name,
            address,
            cuisineType,
            existingRestaurant.getRestaurantOwners(),
            openingHoursSet
        );

        return restaurantRepository.save(updatedRestaurant);
    }
}
