package br.com.fiap.clean_arch.application.usecases;

import br.com.fiap.clean_arch.application.ports.UserRepository;
import br.com.fiap.clean_arch.domain.entities.EProfile;
import br.com.fiap.clean_arch.domain.entities.User;
import br.com.fiap.clean_arch.domain.entities.UserCredentials;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FindUserUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private FindUserUseCase findUserUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindUserById() {
        UserCredentials credentials = UserCredentials.create(1L, "user1", "pass123", ZonedDateTime.now());
        User user = User.create(1L, "John Doe", "123", "john@test.com", 
            "Address 1", credentials, EProfile.CLIENT, ZonedDateTime.now());

        when(userRepository.findById(1L)).thenReturn(user);

        User result = findUserUseCase.execute(1L);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(userRepository, times(1)).findById(1L);
    }
}
