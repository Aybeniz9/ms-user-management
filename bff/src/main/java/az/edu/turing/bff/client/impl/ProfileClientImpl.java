package az.edu.turing.bff.client.impl;

import az.edu.turing.bff.client.ProfileClient;
import az.edu.turing.bff.dto.ProfileDto;
import az.edu.turing.bff.enums.ProfileStatus;
import az.edu.turing.bff.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProfileClientImpl implements ProfileClient {

    private final ProfileService profileService;

    @Override
    public ResponseEntity<ProfileDto> findProfileById(UUID profileId) {
        ProfileDto profileBydId = profileService.getProfileById(profileId);
        return ResponseEntity.ok(profileBydId);
    }

    @Override
    public ResponseEntity<ProfileDto> updateProfile(UUID profileId, ProfileDto profileDto) {
        ProfileDto updateProfile = profileService.updateProfile(profileId, profileDto);
        return ResponseEntity.ok(updateProfile);
    }

    @Override
    public ResponseEntity<Boolean> deleteAllProfiles() {
        boolean deleted = profileService.deleteAllProfiles();
        return ResponseEntity.ok(deleted);
    }

    @Override
    public ResponseEntity<Boolean> deleteProfileById(UUID userId, UUID profileId) {
        boolean deleted = profileService.deleteProfileById(userId,profileId);
        return ResponseEntity.ok(deleted);
    }

    @Override
    public ResponseEntity<List<ProfileDto>> findAllProfiles() {
        List<ProfileDto> allProfiles = profileService.getAllProfiles();
        return ResponseEntity.ok(allProfiles);
    }

    @Override
    public ResponseEntity<ProfileDto> createProfile(ProfileDto profileDto) {
        ProfileDto profile = profileService.createProfile(profileDto);
        return ResponseEntity.ok(profile);
    }

    @Override
    public ResponseEntity<ProfileDto> updateProfileStatusById(UUID userId, ProfileStatus status) {
        ProfileDto updateProfileStatus = profileService.updateProfileStatus(userId, status);
        return ResponseEntity.ok(updateProfileStatus);
    }
}
