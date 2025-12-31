package br.com.fiap.clean_arch.application.usecases;

import br.com.fiap.clean_arch.application.ports.UserRepository;
import br.com.fiap.clean_arch.domain.entities.User;
import br.com.fiap.clean_arch.presentation.dto.CreateUserRequest;
import br.com.fiap.clean_arch.presentation.mappers.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCase {

    private final UserRepository userRepository;

    public CreateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(CreateUserRequest createUserRequest) {
        // Add validations here if needed
        // Is email already in use?
        User user = UserMapper.toDomainEntity(createUserRequest);
        return userRepository.save(user);
    }
}
