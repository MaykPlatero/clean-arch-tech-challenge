package br.com.fiap.clean_arch.application.ports;

import br.com.fiap.clean_arch.domain.entities.MenuItem;

public interface MenuItemRepository {
    MenuItem save(MenuItem menuItem);
    MenuItem findById(Long id);
}

