package az.edu.turing.usermanagmentsystem.controller;

import az.edu.turing.usermanagmentsystem.model.dto.ProfileDto;
import az.edu.turing.usermanagmentsystem.model.dto.UserDto;
import az.edu.turing.usermanagmentsystem.model.enums.ProfileStatus;
import az.edu.turing.usermanagmentsystem.service.ProfileService;
import az.edu.turing.usermanagmentsystem.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
@Validated
public class UserController {

    private final UserService userService;
    private final ProfileService profileService;

    @Transactional(readOnly = true)
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserDto>> findById(@PathVariable @NotNull UUID id) {
        return ResponseEntity.ok(userService.userById(id));
    }

    @Transactional
    @PostMapping("/create")
    public ResponseEntity<UserDto> create(@RequestBody @Valid UserDto userDto) {
        return ResponseEntity.ok(userService.create(userDto));
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<Optional<UserDto>> update(@PathVariable @NotNull UUID id, @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.update(id, userDto));
    }

    @Transactional
    @DeleteMapping("/")
    public ResponseEntity<Boolean> deleteAll() {
        return ResponseEntity.ok(userService.deleteAll());
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.deleteById(id));
    }

    @Transactional
    @PatchMapping("/{id}/email")
    public ResponseEntity<Optional<UserDto>> updateEmail(@PathVariable UUID id, @RequestBody String email) {
        return ResponseEntity.ok(userService.updateEmail(id, email));
    }

    @Transactional(readOnly = true)
    @GetMapping("/{userId}/profile")
    public ResponseEntity<Optional<ProfileDto>> findProfileByUserId(@PathVariable UUID userId) {
        return ResponseEntity.ok(profileService.findProfileByUserId(userId));
    }

    @Transactional
    @PutMapping("/{userId}/profile")
    public ResponseEntity<Optional<ProfileDto>> updateProfile(@PathVariable UUID userId, @RequestBody ProfileDto profileDto) {
        return ResponseEntity.ok(profileService.updateProfile(userId, profileDto));
    }

    @Transactional
    @PatchMapping("/{userId}/profile/status")
    public ResponseEntity<Optional<ProfileDto>> updateProfileStatusById(@PathVariable UUID userId, @RequestBody ProfileStatus status) {
        return ResponseEntity.ok(profileService.updateProfileStatusById(userId, status));
    }

    @Transactional
    @DeleteMapping("/{userId}/profile")
    public ResponseEntity<Boolean> deleteAllProfile() {
        return ResponseEntity.ok(profileService.deleteAllProfile());
    }

    @Transactional
    @DeleteMapping("/{userId}/profile/{profileId}")
    public ResponseEntity<Boolean> deleteProfileById(@PathVariable UUID userId, @PathVariable UUID profileId) {
        return ResponseEntity.ok(profileService.deleteProfileById(profileId));
    }

    @Transactional
    @GetMapping("/{userId}/profile/all")
    public ResponseEntity<List<ProfileDto>> findAllProfiles(@PathVariable UUID userId) {
        return ResponseEntity.ok(profileService.findAllProfiles(userId));
    }

    @Transactional
    @PostMapping("/{userId}/profile/create")
    public ResponseEntity<Optional<ProfileDto>> createProfileByUserId(@PathVariable UUID userId, @RequestBody ProfileDto profileDto) {
        return ResponseEntity.ok(profileService.createProfileByUserId(userId, profileDto));
    }
}
