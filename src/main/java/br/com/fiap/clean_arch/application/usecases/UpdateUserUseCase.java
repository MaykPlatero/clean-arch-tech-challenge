package br.com.fiap.clean_arch.application.usecases;

import br.com.fiap.clean_arch.application.ports.UserRepository;
import br.com.fiap.clean_arch.domain.entities.EProfile;
import br.com.fiap.clean_arch.domain.entities.User;
import br.com.fiap.clean_arch.domain.entities.UserCredentials;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class UpdateUserUseCase {
    private final UserRepository userRepository;

    public UpdateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(Long id, String name, String userIdentification, String email, 
                       String address, String username, String password, String profileStr) {
        User existingUser = userRepository.findById(id);

        if (existingUser == null) {
            throw new IllegalArgumentException("User not found");
        }

        if (name != null) {
            existingUser.setName(name);
        }
        if (userIdentification != null) {
            existingUser.setUserIdentification(userIdentification);
        }
        if (email != null) {
            existingUser.setEmail(email);
        }
        if (address != null) {
            existingUser.setAddress(address);
        }
        if (profileStr != null) {
            existingUser.setProfile(EProfile.valueOf(profileStr.toUpperCase()));
        }

        if (username != null || password != null) {
            UserCredentials existingUserCredentials = existingUser.getUserCredentials();
            existingUserCredentials.updateCredentials(username, password);
        }

        existingUser.setLastUpdate(ZonedDateTime.now());

        return userRepository.save(existingUser);
    }
}
