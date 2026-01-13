package br.com.fiap.clean_arch.application.usecases;

import br.com.fiap.clean_arch.application.ports.RestaurantRepository;
import br.com.fiap.clean_arch.domain.entities.Restaurant;
import br.com.fiap.clean_arch.presentation.dto.request.CreateRestaurantRequest;
import br.com.fiap.clean_arch.presentation.mappers.OpeningHoursMapper;
import org.springframework.stereotype.Service;

@Service
public class UpdateRestaurantUseCase {
    private final RestaurantRepository restaurantRepository;

    public UpdateRestaurantUseCase(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant execute(Long id, CreateRestaurantRequest request) {
        Restaurant existingRestaurant = restaurantRepository.findById(id);
        if (existingRestaurant == null) {
            throw new RuntimeException("Restaurant not found with id: " + id);
        }

        Restaurant updatedRestaurant = Restaurant.create(
            id,
            request.name(),
            request.cuisineType(),
            request.address(),
            existingRestaurant.getRestaurantOwners(),
            OpeningHoursMapper.toDomainEntitySet(request.openingHours())
        );

        return restaurantRepository.save(updatedRestaurant);
    }
}
