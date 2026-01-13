package br.com.fiap.clean_arch.application.usecases;

import br.com.fiap.clean_arch.application.ports.MenuItemRepository;
import br.com.fiap.clean_arch.domain.entities.MenuItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindMenuItemsByRestaurantUseCase {
    private final MenuItemRepository menuItemRepository;

    public FindMenuItemsByRestaurantUseCase(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public List<MenuItem> execute(Long restaurantId) {
        return menuItemRepository.findByRestaurantId(restaurantId);
    }
}
