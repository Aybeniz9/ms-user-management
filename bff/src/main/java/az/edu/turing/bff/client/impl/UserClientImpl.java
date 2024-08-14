package az.edu.turing.bff.client.impl;

import az.edu.turing.bff.client.UserClient;
import az.edu.turing.bff.dto.UserDto;
import az.edu.turing.bff.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@RequiredArgsConstructor
@Service
public class UserClientImpl implements UserClient {

    private final UserService userService;
    @Override
    public ResponseEntity<List<UserDto>> findAll() {
        List<UserDto> allUsers = userService.getAllUsers();
       return ResponseEntity.ok(allUsers);
    }

    @Override
    public ResponseEntity<UserDto> findById(UUID id) {
        UserDto userById = userService.getUserById(id);
        return ResponseEntity.ok(userById);
    }

    @Override
    public ResponseEntity<UserDto> create(UserDto userDto) {
        UserDto user = userService.createUser(userDto);
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<UserDto> update(UUID id, UserDto userDto) {
        UserDto updateUser = userService.updateUser(id, userDto);
        return ResponseEntity.ok(updateUser);
    }

    @Override
    public ResponseEntity<Boolean> deleteAll() {
        boolean deleted = userService.deleteAllUsers();
        return ResponseEntity.ok(deleted);
    }

    @Override
    public ResponseEntity<Boolean> deleteById(UUID id) {
        boolean deleted = userService.deleteUserById(id);
        return ResponseEntity.ok(deleted);
    }

    @Override
    public ResponseEntity<UserDto> updateEmail(UUID id, String email) {
        UserDto updateUserEmail = userService.updateUserEmail(id, email);
        return ResponseEntity.ok(updateUserEmail);
    }
}
