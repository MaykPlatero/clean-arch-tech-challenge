package br.com.fiap.clean_arch.application.usecases;

import br.com.fiap.clean_arch.application.ports.MenuItemRepository;
import br.com.fiap.clean_arch.domain.entities.MenuItem;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UpdateMenuItemUseCase {
    private final MenuItemRepository menuItemRepository;

    public UpdateMenuItemUseCase(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public MenuItem execute(Long id, Long restaurantId, String name, String description, 
                           BigDecimal price, Boolean deliveryItem, String photoUrl) {
        MenuItem existingItem = menuItemRepository.findById(id);
        if (existingItem == null) {
            throw new RuntimeException("Menu item not found with id: " + id);
        }

        MenuItem updatedItem = MenuItem.create(
            id,
            restaurantId,
            name,
            description,
            price,
            deliveryItem,
            photoUrl
        );

        return menuItemRepository.save(updatedItem);
    }
}
