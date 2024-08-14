package az.edu.turing.usermanagmentsystem.model.entity;


import az.edu.turing.usermanagmentsystem.model.enums.ProfileStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;
@Setter
@Data
@Entity(name = "profiles")
public class ProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @CreationTimestamp
    @NotNull
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @NotNull
    @Column(name = "update_at")
    private LocalDateTime updatedAt;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @NotNull
    private ProfileStatus status;

    @NotBlank
    @Size(min = 3, max = 50)
    @Column(name = "username")
    private String username;

    @NotNull
    @Column(name = "profile_photo")
    private byte[] profilePhoto;

    @NotNull
    @Column(name = "description")
    @Size(min = 2, max = 50)
    private String description;

    @Past
    @Column(name = "last_seen_time")
    private LocalDateTime lastSeenTime;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}
