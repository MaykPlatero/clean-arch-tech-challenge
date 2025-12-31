package br.com.fiap.clean_arch.presentation.mappers;

import br.com.fiap.clean_arch.domain.entities.EProfile;
import br.com.fiap.clean_arch.domain.entities.User;
import br.com.fiap.clean_arch.domain.entities.UserCredentials;
import br.com.fiap.clean_arch.infrastructure.persistence.entity.UserEntity;
import br.com.fiap.clean_arch.presentation.dto.CreateRestaurantRequest;
import br.com.fiap.clean_arch.presentation.dto.CreateUserRequest;
import br.com.fiap.clean_arch.presentation.dto.UserResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {
    public static User toDomainEntity(UserEntity userEntity) {
        return User.create(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getUserIdentification(),
                userEntity.getEmail(),
                userEntity.getAddress(),
                UserCredentialsMapper.toDomainEntity(userEntity.getUserCredentials()),
                userEntity.getProfile(),
                userEntity.getLastUpdate()
        );
    }

    public static User toDomainEntity(CreateUserRequest createUserRequest) throws IllegalArgumentException {
        EProfile profile = EProfile.valueOf(createUserRequest.profile());
        UserCredentials userCredentials = UserCredentials.create(createUserRequest.username(), createUserRequest.password(),null);

        return User.create(
                null,
                createUserRequest.name(),
                createUserRequest.userIdentification(),
                createUserRequest.email(),
                createUserRequest.address(),
                userCredentials,
                profile,
                null
        );
    }

    public static UserEntity toPersistenceEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setName(user.getName());
        userEntity.setUserIdentification(user.getUserIdentification());
        userEntity.setEmail(user.getEmail());
        userEntity.setAddress(user.getAddress());
        userEntity.setUserCredentials(UserCredentialsMapper.toPersistenceEntity(user.getUserCredentials()));
        userEntity.setProfile(user.getProfile());
        userEntity.setLastUpdate(user.getLastUpdate());
        return userEntity;
    }

    public static UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getUserIdentification(),
                user.getAddress(),
                user.getProfile().name()
        );
    }
}
