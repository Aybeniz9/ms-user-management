package az.edu.turing.usermanagmentsystem.dao.entity;


import az.edu.turing.usermanagmentsystem.model.enums.ProfileStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Data
@Entity(name = "profiles")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @CreationTimestamp
    @NotNull
    @Column(name = "created_at", updatable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    @NotNull
    @Column(name = "update_at")
    LocalDateTime updatedAt;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @NotNull
    ProfileStatus status;

    @NotBlank
    @Size(min = 3, max = 50)
    @Column(name = "username")
    String username;

    @NotNull
    @Column(name = "profile_photo")
    byte[] profilePhoto;

    @NotNull
    @Column(name = "description")
    @Size(min = 2, max = 50)
    String description;

    @Past
    @Column(name = "last_seen_time")
    LocalDateTime lastSeenTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    UserEntity user;
}
