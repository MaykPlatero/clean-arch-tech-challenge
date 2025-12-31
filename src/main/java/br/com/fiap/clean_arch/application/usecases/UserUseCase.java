package br.com.fiap.clean_arch.application.usecases;

import br.com.fiap.clean_arch.application.dto.CreateUserDTO;
import br.com.fiap.clean_arch.application.ports.UserRepository;
import br.com.fiap.clean_arch.domain.entities.User;
import br.com.fiap.clean_arch.presentation.mappers.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserUseCase {

    private final UserRepository userRepository;

    public UserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(CreateUserDTO createUserDTO) {
        User user = UserMapper.toDomainEntity(createUserDTO);
        return userRepository.save(user);
    }
}
