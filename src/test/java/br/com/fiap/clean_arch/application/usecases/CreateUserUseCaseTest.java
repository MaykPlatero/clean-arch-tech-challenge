package br.com.fiap.clean_arch.application.usecases;

import br.com.fiap.clean_arch.application.ports.UserRepository;
import br.com.fiap.clean_arch.domain.entities.User;
import br.com.fiap.clean_arch.domain.entities.UserCredentials;
import br.com.fiap.clean_arch.domain.entities.EProfile;
import br.com.fiap.clean_arch.presentation.dto.request.CreateUserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateUserUseCaseTest {

    @Mock
    private UserRepository userRepository;

    private CreateUserUseCase createUserUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        createUserUseCase = new CreateUserUseCase(userRepository);
    }

    @Test
    void shouldCreateUserSuccessfully() {
        CreateUserRequest request = new CreateUserRequest("John", "john@test.com", "123", "Address", "client", "username", "password123");
        UserCredentials credentials = UserCredentials.create(1L, "username", "password123", ZonedDateTime.now());
        User savedUser = User.create(1L, "John", "123", "john@test.com", "Address", credentials, EProfile.client, ZonedDateTime.now());
        
        when(userRepository.save(any(User.class))).thenReturn(savedUser);
        
        User result = createUserUseCase.execute(request);
        
        assertNotNull(result);
        assertEquals("John", result.getName());
        verify(userRepository).save(any(User.class));
    }
}
