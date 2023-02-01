package re.belanov.myappone.converters;

import org.springframework.stereotype.Component;
import re.belanov.myappone.dto.UserDto;
import re.belanov.myappone.model.User;


@Component
public class UserDtoConverter {

    public UserDto makeUserDto(User user) {
        return UserDto.builder()
                .Id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .gender(user.getGender())
                .role(user.getRoles())
                .build();
    }

    public User makeUserFromDto(UserDto userDto) {
        return User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .gender(userDto.getGender())
                .build();
    }

//    public Role getRole(String role) {
//        String result = role.replaceAll("[\\[\\]\"]", "");
//
//        return result.equalsIgnoreCase("admin") ? Role.ADMIN : Role.USER;
//
//    }
}
