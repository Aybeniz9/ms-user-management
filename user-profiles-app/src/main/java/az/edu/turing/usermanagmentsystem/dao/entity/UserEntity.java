package az.edu.turing.usermanagmentsystem.dao.entity;

import az.edu.turing.usermanagmentsystem.model.enums.GenderType;
import az.edu.turing.usermanagmentsystem.model.enums.UserStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Entity(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(name = "created_at", nullable = false)
    LocalDateTime createdAt;

    @Column(name = "update_at", nullable = false)
    LocalDateTime updatedAt;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = " surname", nullable = false)
    String surname;

    @Column(name = "email", nullable = false)
    String email;

    @Column(name = "phone_number", nullable = false)
    String phoneNumber;

    @Column(name = "date_of_birth", nullable = false)
    LocalDateTime dateOfBirth;

    @Column(name = "gender", nullable = false)
    GenderType gender;

    @Column(name = "status", nullable = false)
    UserStatus userStatus;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    Set<ProfileEntity> profiles;

}
