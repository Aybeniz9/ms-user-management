package az.edu.turing.bff.client;

import az.edu.turing.bff.dto.ProfileDto;
import az.edu.turing.bff.enums.ProfileStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "profileClient", url = "http://localhost:8085/api/v1/users")
public interface ProfileClient {

    @GetMapping("/{profileId}")
    ResponseEntity<ProfileDto> findProfileById(@PathVariable UUID profileId);

    @PutMapping("/{profileId}")
    ResponseEntity<ProfileDto> updateProfile(@PathVariable UUID profileId, @RequestBody ProfileDto profileDto);

    @DeleteMapping
    ResponseEntity<Boolean> deleteAllProfiles();

    @DeleteMapping("/{userId}/profile/{profileId}")
    ResponseEntity<Boolean> deleteProfileById(@PathVariable UUID userId, @PathVariable UUID profileId);

    @GetMapping("/{userId}")
    ResponseEntity<List<ProfileDto>> findAllProfiles();

    @PostMapping("/profile/create")
    ResponseEntity<ProfileDto> createProfile(@RequestBody ProfileDto profileDto);

    @PatchMapping("/{userId}/profile/status")
    ResponseEntity<ProfileDto> updateProfileStatusById(@PathVariable UUID userId, @RequestBody ProfileStatus status);
}