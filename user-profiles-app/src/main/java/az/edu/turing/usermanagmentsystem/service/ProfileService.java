package az.edu.turing.usermanagmentsystem.service;

import az.edu.turing.usermanagmentsystem.exception.UserNotFoundException;
import az.edu.turing.usermanagmentsystem.mapper.ProfileMapper;
import az.edu.turing.usermanagmentsystem.model.dto.ProfileDto;
import az.edu.turing.usermanagmentsystem.model.entity.ProfileEntity;
import az.edu.turing.usermanagmentsystem.model.entity.UserEntity;
import az.edu.turing.usermanagmentsystem.model.enums.ProfileStatus;
import az.edu.turing.usermanagmentsystem.model.enums.UserStatus;
import az.edu.turing.usermanagmentsystem.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProfileService {

    private final ProfileMapper profileMapper;
    private final ProfileRepository profileRepository;

    public List<ProfileDto> findAllProfiles(UUID userId) {
        List<ProfileEntity> profiles = profileRepository.findByUserId(userId);
        return profileMapper.entityListToDtoList(profiles);

    }

    public Optional<ProfileDto> createProfileByUserId(UUID id, ProfileDto profileDto) {

        ProfileEntity profileEntity = profileMapper.dtoToEntity(profileDto);

        profileEntity.setId(id);
        profileEntity.setCreatedAt(LocalDateTime.now());
        profileEntity.setUpdatedAt(LocalDateTime.now());

        ProfileEntity savedProfile = profileRepository.save(profileEntity);

        log.info("Profile created with id " + savedProfile.getId());
        return Optional.ofNullable(profileMapper.entityToDto(savedProfile));
    }


    public Optional<ProfileDto> updateProfile(UUID userId, ProfileDto profileDto) {
        return profileRepository.findById(userId)
                .map(existingProfile -> {
                    ProfileEntity updatedProfile = profileMapper.dtoToEntity(profileDto);

                    updatedProfile.setId(existingProfile.getId());
                    updatedProfile.setUpdatedAt(LocalDateTime.now());

                    profileRepository.save(updatedProfile);
                    return Optional.ofNullable(profileMapper.entityToDto(updatedProfile));
                })
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));
    }

    public Optional<ProfileDto> updateProfileStatusById(UUID id, ProfileStatus status) {
        return profileRepository.findById(id)
                .map(existingProfile -> {

                    existingProfile.setStatus(status);
                    existingProfile.setUpdatedAt(LocalDateTime.now());

                    profileRepository.save(existingProfile);

                    return Optional.ofNullable(profileMapper.entityToDto(existingProfile));
                })
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
    }

    public Optional<ProfileDto> findProfileByUserId(UUID id) {
        log.info("Find profile by id: " + id);
        return profileRepository.findById(id).map(profileMapper::entityToDto);


    }

    public boolean deleteAllProfile() {
        List<ProfileEntity> profiles = profileRepository.findAll();

        profiles.forEach(profile -> profile.setStatus(ProfileStatus.DEACTIVATED));

        profileRepository.saveAll(profiles);
        profileRepository.deleteAll();

        log.info("All profiles have been marked as deleted and deleted from the database");

        return true;

    }//todo


    public boolean deleteProfileById(UUID id) {
        return profileRepository.findById(id)
                .map(profileEntity -> {
                    profileEntity.setStatus(ProfileStatus.DEACTIVATED);

                    profileRepository.save(profileEntity);
                    profileRepository.delete(profileEntity);

                    log.info("User with ID {} has been marked as deactivated and removed from the database", id);
                    return true;
                })
                .orElseGet(() -> {
                    log.warn("User with ID {} not found", id);
                    return false;
                });
    }
}//todo

