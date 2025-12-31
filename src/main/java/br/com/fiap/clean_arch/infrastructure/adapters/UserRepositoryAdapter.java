package br.com.fiap.clean_arch.infrastructure.adapters;

import br.com.fiap.clean_arch.application.ports.UserRepository;
import br.com.fiap.clean_arch.domain.entities.User;
import br.com.fiap.clean_arch.infrastructure.persistence.entity.UserEntity;
import br.com.fiap.clean_arch.infrastructure.persistence.repository.UserJpaRepository;
import br.com.fiap.clean_arch.presentation.mappers.UserMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static br.com.fiap.clean_arch.presentation.mappers.UserMapper.toDomainEntity;
import static br.com.fiap.clean_arch.presentation.mappers.UserMapper.toPersistenceEntity;

@Component
public class UserRepositoryAdapter implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    public UserRepositoryAdapter(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = toPersistenceEntity(user);
        UserEntity savedUser = userJpaRepository.save(userEntity);
        return toDomainEntity(savedUser);
    }

    @Override
    public User findById(Long id) {
        Optional<UserEntity> userEntity = userJpaRepository.findById(id);
        return userEntity.map(UserMapper::toDomainEntity).orElse(null);
    }
}
