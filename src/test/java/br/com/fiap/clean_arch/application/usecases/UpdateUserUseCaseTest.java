package br.com.fiap.clean_arch.application.usecases;

import br.com.fiap.clean_arch.application.ports.UserRepository;
import br.com.fiap.clean_arch.domain.entities.User;
import br.com.fiap.clean_arch.domain.entities.UserCredentials;
import br.com.fiap.clean_arch.domain.entities.EProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UpdateUserUseCaseTest {

    @Mock
    private UserRepository userRepository;

    private UpdateUserUseCase updateUserUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        updateUserUseCase = new UpdateUserUseCase(userRepository);
    }

    @Test
    void shouldUpdateUser() {
        Long userId = 1L;
        
        UserCredentials existingCredentials = UserCredentials.create(1L, "olduser", "oldpass123", ZonedDateTime.now());
        User existingUser = User.create(userId, "John", "123456789", "john@test.com", "Rua A, 123", existingCredentials, EProfile.CLIENT, ZonedDateTime.now());
        
        UserCredentials updatedCredentials = UserCredentials.create(1L, "newuser", "newpass123", ZonedDateTime.now());
        User updatedUser = User.create(userId, "John Updated", "987654321", "john.updated@test.com", "Rua B, 456", updatedCredentials, EProfile.ADMIN, ZonedDateTime.now());
        
        when(userRepository.findById(userId)).thenReturn(existingUser);
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);
        
        User result = updateUserUseCase.execute(userId, "John Updated", "987654321", "john.updated@test.com", "Rua B, 456", "newuser", "newpass123", "admin");
        
        assertNotNull(result);
        assertEquals("John Updated", result.getName());
        verify(userRepository).findById(userId);
        verify(userRepository).save(any(User.class));
    }
}
