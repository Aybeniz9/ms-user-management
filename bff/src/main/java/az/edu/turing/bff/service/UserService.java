package az.edu.turing.bff.service;

import az.edu.turing.bff.client.UserClient;
import az.edu.turing.bff.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {
    @Lazy
    private final UserClient userClient;

    public List<UserDto> getAllUsers() {
        ResponseEntity<List<UserDto>> response = userClient.findAll();
        return response.getBody();
    }

    public UserDto getUserById(UUID id) {
        ResponseEntity<UserDto> response = userClient.findById(id);
        return response.getBody();
    }

    public UserDto createUser(UserDto userDto) {
        ResponseEntity<UserDto> response = userClient.create(userDto);
        return response.getBody();
    }

    public UserDto updateUser(UUID id, UserDto userDto) {
        ResponseEntity<UserDto> response = userClient.update(id, userDto);
        return response.getBody();
    }

    public boolean deleteAllUsers() {
        ResponseEntity<Boolean> response = userClient.deleteAll();
        return response.getBody();
    }

    public boolean deleteUserById(UUID id) {
        ResponseEntity<Boolean> response = userClient.deleteById(id);
        return response.getBody();
    }

    public UserDto updateUserEmail(UUID id, String email) {
        ResponseEntity<UserDto> response = userClient.updateEmail(id, email);
        return response.getBody();
    }
}
