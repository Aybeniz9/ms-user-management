package az.edu.turing.bff.controller;

import az.edu.turing.bff.dto.ProfileDto;
import az.edu.turing.bff.enums.ProfileStatus;
import az.edu.turing.bff.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/allProfile")
    public ResponseEntity<List<ProfileDto>> findAllProfiles() {
        return ResponseEntity.ok(profileService.getAllProfiles());
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<Optional<ProfileDto>> findProfileById(@PathVariable UUID id) {
        return ResponseEntity.ok(Optional.ofNullable(profileService.getProfileById(id)));
    }

    @PostMapping("/profile/create")
    public ResponseEntity<ProfileDto> createProfile(@RequestBody ProfileDto profileDto) {
        return ResponseEntity.ok(profileService.createProfile(profileDto));
    }

    @PutMapping("/{profileId}")
    public ResponseEntity<Optional<ProfileDto>> updateProfile(@PathVariable UUID id, @RequestBody ProfileDto profileDto) {
        return ResponseEntity.ok(Optional.ofNullable(profileService.updateProfile(id, profileDto)));
    }

    @GetMapping("/deleteAllProfiles")
    public ResponseEntity<Boolean> deleteAllProfiles() {
        return ResponseEntity.ok(profileService.deleteAllProfiles());
    }

    @DeleteMapping("/{userId}/profile/{profileId}")
    public ResponseEntity<Boolean> deleteProfileById(@PathVariable UUID userId, @PathVariable UUID profileId) {
        return ResponseEntity.ok(profileService.deleteProfileById(userId,profileId));
    }

    @PatchMapping("/{userId}/profile/status")
    public ResponseEntity<Optional<ProfileDto>> updateProfileStatus(@PathVariable UUID userId, @RequestBody ProfileStatus status) {
        return ResponseEntity.ok(Optional.ofNullable(profileService.updateProfileStatus(userId, status)));
    }
}
