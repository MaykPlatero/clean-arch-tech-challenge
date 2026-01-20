package br.com.fiap.clean_arch.infrastructure.adapters;

import br.com.fiap.clean_arch.domain.entities.EProfile;
import br.com.fiap.clean_arch.domain.entities.User;
import br.com.fiap.clean_arch.domain.entities.UserCredentials;
import br.com.fiap.clean_arch.infrastructure.persistence.entity.UserCredentialsEntity;
import br.com.fiap.clean_arch.infrastructure.persistence.entity.UserEntity;
import br.com.fiap.clean_arch.infrastructure.persistence.repository.UserJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.ZonedDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserRepositoryAdapterTest {

    @Mock
    private UserJpaRepository userJpaRepository;

    @InjectMocks
    private UserRepositoryAdapter userRepositoryAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindUserById() {
        UserCredentialsEntity credentialsEntity = new UserCredentialsEntity();
        credentialsEntity.setId(1L);
        credentialsEntity.setUsername("user1");
        credentialsEntity.setPassword("pass123");
        credentialsEntity.setLastUpdate(ZonedDateTime.now());

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setName("John");
        userEntity.setEmail("john@test.com");
        userEntity.setUserIdentification("123");
        userEntity.setAddress("Address");
        userEntity.setProfile(EProfile.CLIENT);
        userEntity.setUserCredentials(credentialsEntity);
        userEntity.setLastUpdate(ZonedDateTime.now());

        when(userJpaRepository.findById(1L)).thenReturn(Optional.of(userEntity));

        User result = userRepositoryAdapter.findById(1L);

        assertNotNull(result);
        assertEquals("John", result.getName());
        verify(userJpaRepository, times(1)).findById(1L);
    }

    @Test
    void shouldDeleteUserById() {
        userRepositoryAdapter.deleteById(1L);

        verify(userJpaRepository, times(1)).deleteById(1L);
    }
}
