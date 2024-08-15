package az.edu.turing.usermanagmentsystem.model.dto;

import az.edu.turing.usermanagmentsystem.model.enums.ProfileStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {
    private UUID id;

    @NotNull
    @CreationTimestamp
    private LocalDateTime createdAt;

    @NotNull
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @NotNull
    private ProfileStatus status;

    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    @NotNull
    private byte[] profilePhoto;

    @NotNull
    @Size(min = 2, max = 50)
    private String description;


    @Past
    private LocalDateTime lastSeenTime;
}
