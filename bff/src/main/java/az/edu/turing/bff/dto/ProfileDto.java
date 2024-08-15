package az.edu.turing.bff.dto;

import az.edu.turing.bff.enums.ProfileStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {
    private UUID id;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private LocalDateTime updatedAt;
    @NotNull
    private ProfileStatus status;

    @NotBlank
    private String username;

    @NotNull
    private byte[] profilePhoto;

    @NotNull
    private String description;

    @Past
    private LocalDateTime lastSeenTime;
}
