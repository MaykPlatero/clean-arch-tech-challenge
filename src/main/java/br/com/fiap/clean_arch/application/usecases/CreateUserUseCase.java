package br.com.fiap.clean_arch.application.usecases;

import br.com.fiap.clean_arch.application.ports.UserRepository;
import br.com.fiap.clean_arch.domain.entities.EProfile;
import br.com.fiap.clean_arch.domain.entities.User;
import br.com.fiap.clean_arch.domain.entities.UserCredentials;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class CreateUserUseCase {

    private final UserRepository userRepository;

    public CreateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(String name, String userIdentification, String email, String address, 
                       String username, String password, String profileStr) {
        EProfile profile = EProfile.valueOf(profileStr.toUpperCase());
        UserCredentials userCredentials = UserCredentials.create(username, password, null);
        
        User user = User.create(
            name,
            userIdentification,
            email,
            address,
            userCredentials,
            profile,
            ZonedDateTime.now()
        );

        return userRepository.save(user);
    }
}
