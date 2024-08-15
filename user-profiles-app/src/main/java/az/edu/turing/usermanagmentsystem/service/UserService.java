package az.edu.turing.usermanagmentsystem.service;

import az.edu.turing.usermanagmentsystem.exception.UserNotFoundException;
import az.edu.turing.usermanagmentsystem.mapper.UserMapper;
import az.edu.turing.usermanagmentsystem.model.dto.UserDto;
import az.edu.turing.usermanagmentsystem.dao.entity.UserEntity;
import az.edu.turing.usermanagmentsystem.model.enums.UserStatus;
import az.edu.turing.usermanagmentsystem.dao.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {

    private final UserMapper mapper;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        List<UserEntity> all = userRepository.findAll();
        List<UserDto> userDto = mapper.entityListToDtoList(all);
        if (userDto == null) {
            throw new UserNotFoundException("Aybeniz Tapılmadı");
        }
        return userDto;
    }

    @Transactional
    public UserDto create(UserDto userDto) {

        UserEntity userEntity = mapper.dtoToEntity(userDto);

        userEntity.setCreatedAt(LocalDateTime.now());
        userEntity.setUpdatedAt(LocalDateTime.now());
        userEntity.setUserStatus(UserStatus.ACTIVE);
        UserEntity savedEntity = userRepository.save(userEntity);

        return mapper.entityToDto(savedEntity);
    }

    @Transactional(readOnly = true)
    public Optional<UserDto> userById(UUID id) {
        return userRepository.findById(id).map(mapper::entityToDto);
    }

    @Transactional
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

    @Transactional
    public boolean deleteAll() {
        List<UserEntity> users = userRepository.findAll();

        users.forEach(user -> user.setUserStatus(UserStatus.DELETED));

        userRepository.saveAll(users);
        userRepository.deleteAll();

        log.info("All users have been marked as deleted and deleted from the database");

        return true;
    }

    @Transactional
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

    @Transactional
    public Optional<UserDto> updateEmail(UUID id, String email) {
        return userRepository.findById(id)
                .map(userEntity -> {
                    userEntity.setEmail(email);
                    UserEntity savedEntity = userRepository.save(userEntity);
                    return Optional.of(mapper.entityToDto(savedEntity));
                }).orElseThrow(() -> new UserNotFoundException("User not found"));

    }
}
