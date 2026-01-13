package br.com.fiap.clean_arch.application.usecases;

import br.com.fiap.clean_arch.application.ports.RestaurantRepository;
import br.com.fiap.clean_arch.domain.entities.Restaurant;
import org.springframework.stereotype.Service;

@Service
public class FindRestaurantUseCase {
    private final RestaurantRepository restaurantRepository;

    public FindRestaurantUseCase(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant execute(Long id) {
        return restaurantRepository.findById(id);
    }
}
