package az.edu.turing.bff.service;

import az.edu.turing.bff.client.ProfileClient;
import az.edu.turing.bff.client.UserClient;
import az.edu.turing.bff.dto.ProfileDto;
import az.edu.turing.bff.dto.UserDto;
import az.edu.turing.bff.enums.ProfileStatus;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProfileService {
    @Lazy
    private final ProfileClient profileClient;

    public List<ProfileDto> getAllProfiles() {
        ResponseEntity<List<ProfileDto>> response = profileClient.findAllProfiles();
        return response.getBody();
    }

    public ProfileDto getProfileById(UUID id) {
        ResponseEntity<ProfileDto> response = profileClient.findProfileById(id);
        return response.getBody();
    }

    public ProfileDto createProfile(ProfileDto profileDto) {
        ResponseEntity<ProfileDto> response = profileClient.createProfile(profileDto);
        return response.getBody();
    }

    public ProfileDto updateProfile(UUID id, ProfileDto profileDto) {
        ResponseEntity<ProfileDto> response = profileClient.updateProfile(id, profileDto);
        return response.getBody();
    }

    public boolean deleteAllProfiles() {
        ResponseEntity<Boolean> response = profileClient.deleteAllProfiles();
        return response.getBody();
    }

    public boolean deleteProfileById(UUID userId,UUID profileId) {
        ResponseEntity<Boolean> response = profileClient.deleteProfileById(userId, profileId);
        return response.getBody();
    }

    public ProfileDto updateProfileStatus(UUID id, ProfileStatus status) {
        ResponseEntity<ProfileDto> response = profileClient.updateProfileStatusById(id, status);
        return response.getBody();
    }
}
