package br.com.fiap.clean_arch.application.usecases;

import br.com.fiap.clean_arch.application.ports.UserRepository;
import br.com.fiap.clean_arch.domain.entities.EProfile;
import br.com.fiap.clean_arch.domain.entities.User;
import br.com.fiap.clean_arch.domain.entities.UserCredentials;
import br.com.fiap.clean_arch.presentation.dto.UpdateUserRequest;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class UpdateUserUseCase {
    private final UserRepository userRepository;

    public UpdateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(Long id, UpdateUserRequest updateUserRequest) {
        User existingUser = userRepository.findById(id);

        if (existingUser == null) {
            throw new IllegalArgumentException("User not found");
        }

        updateUserParams(updateUserRequest, existingUser);

        return userRepository.save(existingUser);
    }

    private static void updateUserParams(UpdateUserRequest updateUserRequest, User existingUser) {
        if (updateUserRequest.name() != null) {
            existingUser.setName(updateUserRequest.name());
        }
        if (updateUserRequest.userIdentification() != null) {
            existingUser.setUserIdentification(updateUserRequest.userIdentification());
        }
        if (updateUserRequest.email() != null) {
            existingUser.setEmail(updateUserRequest.email());
        }
        if (updateUserRequest.address() != null) {
            existingUser.setAddress(updateUserRequest.address());
        }
        if (updateUserRequest.profile() != null) {
            existingUser.setProfile(EProfile.valueOf(updateUserRequest.profile()));
        }
        if (updateUserRequest.username() != null && updateUserRequest.password() != null) {
            existingUser.setUserCredentials(UserCredentials.create(updateUserRequest.username(), updateUserRequest.password(), null));
        }

        existingUser.setLastUpdate(ZonedDateTime.now());
    }
}
