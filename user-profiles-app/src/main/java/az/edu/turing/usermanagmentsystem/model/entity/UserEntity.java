package az.edu.turing.usermanagmentsystem.model.entity;

import az.edu.turing.usermanagmentsystem.model.enums.GenderType;
import az.edu.turing.usermanagmentsystem.model.enums.UserStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Entity(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "update_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name= " surname" ,nullable = false)
    private String surname;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name ="date_of_birth", nullable = false )
    private LocalDateTime dateOfBirth;

    @Column(name = "gender", nullable = false)
    private GenderType gender;

    @Column(name = "status" , nullable = false)
    private UserStatus userStatus;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)//todo
    private Set<ProfileEntity> profiles;



}
