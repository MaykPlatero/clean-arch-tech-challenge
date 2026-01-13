package br.com.fiap.clean_arch.application.usecases;

import br.com.fiap.clean_arch.application.ports.MenuItemRepository;
import br.com.fiap.clean_arch.domain.entities.MenuItem;
import br.com.fiap.clean_arch.presentation.dto.request.CreateMenuItemRequest;
import org.springframework.stereotype.Service;

@Service
public class UpdateMenuItemUseCase {
    private final MenuItemRepository menuItemRepository;

    public UpdateMenuItemUseCase(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public MenuItem execute(Long id, CreateMenuItemRequest request) {
        MenuItem existingItem = menuItemRepository.findById(id);
        if (existingItem == null) {
            throw new RuntimeException("Menu item not found with id: " + id);
        }

        MenuItem updatedItem = MenuItem.create(
            id,
            request.restaurantId(),
            request.name(),
            request.description(),
            request.price(),
            request.deliveryItem(),
            request.photoUrl()
        );

        return menuItemRepository.save(updatedItem);
    }
}
