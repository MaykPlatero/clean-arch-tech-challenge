package br.com.fiap.clean_arch.application.ports;

import br.com.fiap.clean_arch.domain.entities.MenuItem;

import java.util.List;

public interface MenuItemRepository {
    MenuItem save(MenuItem menuItem);
    MenuItem findById(Long id);
    List<MenuItem> findByRestaurantId(Long restaurantId);
    void deleteById(Long id);
}
