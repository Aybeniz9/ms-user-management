package az.edu.turing.usermanagmentsystem.controller;

import az.edu.turing.usermanagmentsystem.model.dto.ProfileDto;
import az.edu.turing.usermanagmentsystem.model.dto.UserDto;
import az.edu.turing.usermanagmentsystem.model.enums.ProfileStatus;
import az.edu.turing.usermanagmentsystem.service.ProfileService;
import az.edu.turing.usermanagmentsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final ProfileService profileService;

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserDto>> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.userById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.create(userDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<UserDto>> update(@PathVariable UUID id, @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.update(id,userDto));
    }

    @DeleteMapping("/")
    public ResponseEntity<Boolean> deleteAll() {
        return ResponseEntity.ok(userService.deleteAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.deleteById(id));
    }

    @PatchMapping("/{id}/email")
    public ResponseEntity<Optional<UserDto>> updateEmail(@PathVariable UUID id, @RequestBody String email) {
        return ResponseEntity.ok(userService.updateEmail(id, email));
    }

    @GetMapping("/{userId}/profile")
    public ResponseEntity<Optional<ProfileDto>> findProfileByUserId(@PathVariable UUID userId) {
        return ResponseEntity.ok(profileService.findProfileByUserId(userId));
    }

    @PutMapping("/{userId}/profile")
    public ResponseEntity<Optional<ProfileDto>> updateProfile(@PathVariable UUID userId, @RequestBody ProfileDto profileDto) {
        return ResponseEntity.ok(profileService.updateProfile(userId,profileDto));
    }

    @PatchMapping("/{userId}/profile/status")
    public ResponseEntity<Optional<ProfileDto>> updateProfileStatusById(@PathVariable UUID userId, @RequestBody ProfileStatus status) {
        return ResponseEntity.ok(profileService.updateProfileStatusById(userId, status));
    }

    @DeleteMapping("/{userId}/profile")
    public ResponseEntity<Boolean> deleteAllProfile() {
        return ResponseEntity.ok(profileService.deleteAllProfile());
    }

    @DeleteMapping("/{userId}/profile/{profileId}")
    public ResponseEntity<Boolean> deleteProfileById(@PathVariable UUID userId, @PathVariable UUID profileId) {
        return ResponseEntity.ok(profileService.deleteProfileById(profileId));
    }

    @GetMapping("/{userId}/profile/all")
    public ResponseEntity<List<ProfileDto>> findAllProfiles(@PathVariable UUID userId) {
        return ResponseEntity.ok(profileService.findAllProfiles(userId));
    }

    @PostMapping("/{userId}/profile/create")
    public ResponseEntity<Optional<ProfileDto>> createProfileByUserId(@PathVariable UUID userId, @RequestBody ProfileDto profileDto) {
        return ResponseEntity.ok(profileService.createProfileByUserId(userId, profileDto));
    }
}
