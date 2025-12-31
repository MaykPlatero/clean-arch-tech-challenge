package br.com.fiap.clean_arch.application.usecases;

import br.com.fiap.clean_arch.application.ports.UserRepository;
import br.com.fiap.clean_arch.domain.entities.User;
import org.springframework.stereotype.Service;

@Service
public class FindUserUseCase {

    private final UserRepository userRepository;

    public FindUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(Long id) {
        return userRepository.findById(id);
    }
}
