package br.com.fiap.clean_arch.application.usecases;

import br.com.fiap.clean_arch.application.ports.MenuItemRepository;
import br.com.fiap.clean_arch.domain.entities.MenuItem;
import org.springframework.stereotype.Service;

@Service
public class FindMenuItemUseCase {
    private final MenuItemRepository menuItemRepository;

    public FindMenuItemUseCase(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public MenuItem execute(Long id) {
        MenuItem menuItem = menuItemRepository.findById(id);
        if (menuItem == null) {
            throw new RuntimeException("Menu item not found with id: " + id);
        }
        return menuItem;
    }
}
