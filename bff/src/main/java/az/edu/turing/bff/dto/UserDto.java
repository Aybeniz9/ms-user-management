package az.edu.turing.bff.dto;

import az.edu.turing.bff.enums.GenderType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private UUID id;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private LocalDateTime updatedAt;

    @NotBlank
    @Size(min = 2 ,max = 50)
    private String name;

    @NotBlank
    @Size(min = 2 , max = 50)
    private String surname;

    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "\\+?[0-9]{7,15}")
    private String phoneNumber;

    @Past
    private LocalDateTime dateOfBirth;

    @NotNull
    private GenderType gender;

}
