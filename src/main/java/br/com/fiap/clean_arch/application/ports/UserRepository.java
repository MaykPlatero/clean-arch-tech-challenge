package br.com.fiap.clean_arch.application.ports;

import br.com.fiap.clean_arch.domain.entities.User;

public interface UserRepository {
    User save(User user);
    User findById(Long id);
}
