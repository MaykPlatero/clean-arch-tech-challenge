package br.com.fiap.clean_arch.application.usecases;

import br.com.fiap.clean_arch.application.ports.MenuItemRepository;
import br.com.fiap.clean_arch.application.ports.RestaurantRepository;
import br.com.fiap.clean_arch.domain.entities.MenuItem;
import br.com.fiap.clean_arch.domain.entities.Restaurant;
import br.com.fiap.clean_arch.presentation.dto.request.CreateMenuItemRequest;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class CreateMenuItemUseCase {
    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;

    public CreateMenuItemUseCase(MenuItemRepository menuItemRepository, RestaurantRepository restaurantRepository) {
        this.menuItemRepository = menuItemRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public MenuItem execute(CreateMenuItemRequest request) {
        Restaurant restaurant = restaurantRepository.findById(request.restaurantId());
        if (restaurant == null) {
            throw new IllegalArgumentException("Restaurant with ID " + request.restaurantId() + " not found.");
        }

        MenuItem menuItem = MenuItem.create(
            request.restaurantId(),
            request.name(),
            request.price(),
            request.deliveryItem(),
            request.photoUrl(),
            ZonedDateTime.now()
        );

        return menuItemRepository.save(menuItem);
    }
}

