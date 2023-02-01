package re.belanov.myappone.dto;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import lombok.*;
import re.belanov.myappone.model.Role;

import java.util.Set;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NonNull
    private Integer Id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    @Email
    private String email;
    @NonNull
    private String gender;
    @NonNull
    @Enumerated(EnumType.STRING)
    private Set<Role> role;

    @Override
    public String toString() {
        return
                "Id=" + Id +
                        ", firstName='" + firstName + '\'' +
                        ", lastName='" + lastName + '\'' +
                        ", email='" + email + '\'' +
                        ", gender='" + gender + '\'' +
                        ", role='" + role + '\'' +
                        '}';
    }
}
