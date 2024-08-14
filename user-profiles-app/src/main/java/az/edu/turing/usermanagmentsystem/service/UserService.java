package az.edu.turing.usermanagmentsystem.service;

import az.edu.turing.usermanagmentsystem.exception.UserNotFoundException;
import az.edu.turing.usermanagmentsystem.mapper.UserMapper;
import az.edu.turing.usermanagmentsystem.model.dto.UserDto;
import az.edu.turing.usermanagmentsystem.model.entity.UserEntity;
import az.edu.turing.usermanagmentsystem.model.enums.UserStatus;
import az.edu.turing.usermanagmentsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static az.edu.turing.usermanagmentsystem.model.enums.UserStatus.DELETED;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {

    private final UserMapper mapper;
    private final UserRepository userRepository;

    public List<UserDto> findAll() {
        List<UserEntity> all = userRepository.findAll();
        List<UserDto> userDto = mapper.entityListToDtoList(all);
        if (userDto == null) {
            throw new UserNotFoundException("Aybeniz Tapılmadı");
        }
        return userDto;
    }

    public UserDto create(UserDto userDto) {

        UserEntity userEntity = mapper.dtoToEntity(userDto);

        userEntity.setCreatedAt(LocalDateTime.now());
        userEntity.setUpdatedAt(LocalDateTime.now());
        userEntity.setUserStatus(UserStatus.ACTIVE);
        UserEntity savedEntity = userRepository.save(userEntity);

        return mapper.entityToDto(savedEntity);
    }

    public Optional<UserDto> userById(UUID id) {
        return userRepository.findById(id).map(mapper::entityToDto);
    }

    public Optional<UserDto> update(UUID id, UserDto userDto) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("user not found"));

        userEntity.setUpdatedAt(LocalDateTime.now());
        userEntity.setName(userDto.getName());
        userEntity.setSurname(userDto.getSurname());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setGender(userDto.getGender());
        userEntity.setDateOfBirth(userDto.getDateOfBirth());
        userEntity.setPhoneNumber(userDto.getPhoneNumber());

        userRepository.save(userEntity);

        UserDto updatedUserDto = mapper.entityToDto(userEntity);
        return Optional.of(updatedUserDto);
    }

    public boolean deleteAll() {
        List<UserEntity> users = userRepository.findAll();

        users.forEach(user -> user.setUserStatus(UserStatus.DELETED));

        userRepository.saveAll(users);
        userRepository.deleteAll();

        log.info("All users have been marked as deleted and deleted from the database");

        return true;
    }

    public boolean deleteById(UUID id) {
        return userRepository.findById(id)
                .map(userEntity -> {
                    userEntity.setUserStatus(UserStatus.DELETED);

                    userRepository.save(userEntity);
                    userRepository.delete(userEntity);

                    log.info("User with ID {} has been marked as deleted and removed from the database", id);
                    return true;
                })
                .orElseGet(() -> {
                    log.warn("User with ID {} not found", id);
                    return false;
                });
    }


    public Optional<UserDto> updateEmail(UUID id , String email) {
        return userRepository.findById(id)
                .map(userEntity -> {
                    userEntity.setEmail(email);
                    UserEntity savedEntity = userRepository.save(userEntity);
                    return Optional.of(mapper.entityToDto(savedEntity));
                }).orElseThrow(() -> new UserNotFoundException("User not found"));

    }
}
