package az.edu.turing.bff.controller;

import az.edu.turing.bff.dto.UserDto;
import az.edu.turing.bff.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserDto>> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(Optional.ofNullable(userService.getUserById(id)));
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.createUser(userDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<UserDto>> update(@PathVariable UUID id, @RequestBody UserDto userDto) {
        return ResponseEntity.ok(Optional.ofNullable(userService.updateUser(id, userDto)));
    }

    @DeleteMapping("/")
    public ResponseEntity<Boolean> deleteAll() {
        return ResponseEntity.ok(userService.deleteAllUsers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.deleteUserById(id));
    }

    @PatchMapping("/{id}/email")
    public ResponseEntity<Optional<UserDto>> updateEmail(@PathVariable UUID id, @RequestBody String email) {
        return ResponseEntity.ok(Optional.ofNullable(userService.updateUserEmail(id, email)));
    }
}
