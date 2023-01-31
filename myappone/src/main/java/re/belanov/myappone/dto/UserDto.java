package re.belanov.myappone.dto;


import jakarta.validation.constraints.Email;
import lombok.*;

@Data
@Builder
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
    private String role;

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
