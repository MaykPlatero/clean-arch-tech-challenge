package br.com.fiap.clean_arch.application.usecases;

import br.com.fiap.clean_arch.application.ports.MenuItemRepository;
import br.com.fiap.clean_arch.application.ports.RestaurantRepository;
import br.com.fiap.clean_arch.domain.entities.MenuItem;
import br.com.fiap.clean_arch.domain.entities.Restaurant;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Service
public class CreateMenuItemUseCase {
    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;

    public CreateMenuItemUseCase(MenuItemRepository menuItemRepository, RestaurantRepository restaurantRepository) {
        this.menuItemRepository = menuItemRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public MenuItem execute(Long restaurantId, String name, String description, BigDecimal price, 
                           Boolean deliveryItem, String photoUrl) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId);
        if (restaurant == null) {
            throw new IllegalArgumentException("Restaurant with ID " + restaurantId + " not found.");
        }

        MenuItem menuItem = MenuItem.create(
            restaurantId,
            name,
            description,
            price,
            deliveryItem,
            photoUrl,
            ZonedDateTime.now()
        );

        return menuItemRepository.save(menuItem);
    }
}
