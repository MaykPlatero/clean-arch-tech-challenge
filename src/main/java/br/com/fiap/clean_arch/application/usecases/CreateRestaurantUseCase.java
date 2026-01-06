package br.com.fiap.clean_arch.application.usecases;

import br.com.fiap.clean_arch.application.ports.RestaurantRepository;
import br.com.fiap.clean_arch.application.ports.UserRepository;
import br.com.fiap.clean_arch.domain.entities.OpeningHours;
import br.com.fiap.clean_arch.domain.entities.Restaurant;
import br.com.fiap.clean_arch.domain.entities.User;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CreateRestaurantUseCase {
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    public CreateRestaurantUseCase(RestaurantRepository restaurantRepository, UserRepository userRepository) {
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    public Restaurant execute(String name, String address, String cuisineType,
                              List<Long> ownerIds, Set<OpeningHours> openingHoursSet) {

        Set<User> restaurantUsers = new HashSet<>();
        for (Long ownerId : ownerIds) {
            User user = userRepository.findById(ownerId);
            if (user != null) {
                restaurantUsers.add(user);
            } else {
                throw new IllegalArgumentException("User with ID " + ownerId + " not found.");
            }
        }

        Restaurant restaurant = Restaurant.create(name, address, cuisineType, restaurantUsers, openingHoursSet);

        return restaurantRepository.save(restaurant);
    }
}
