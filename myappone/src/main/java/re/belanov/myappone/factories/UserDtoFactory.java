package re.belanov.myappone.factories;

import org.springframework.stereotype.Component;
import re.belanov.myappone.dto.UserDto;
import re.belanov.myappone.model.User;

@Component
public class UserDtoFactory {

    public UserDto makeUserDto(User user){
        return UserDto.builder()
                .Id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .gender(user.getGender())
                .role(user.getRoles().toString())
                .build();
    }
}
