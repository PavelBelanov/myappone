package re.belanov.myappone.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import re.belanov.myappone.dto.UserDto;
import re.belanov.myappone.exceptions.BadRequestException;
import re.belanov.myappone.factories.UserDtoFactory;
import re.belanov.myappone.model.User;
import re.belanov.myappone.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController()
@AllArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    private final UserDtoFactory userDtoFactory;

    public static final String GET_USER_BY_ID = "/api/users/{id}";
    public static final String CREATE_USER = "/api/create";
    public static final String GET_ALL_USERS = "/api/users";
    public static final String DELETE_BY_ID = "/api/delete/{id}";

    public static final String UPDATE_USER_BY_ID = "/api/update/{id}";
    public static final String GET_USER_BY_GENDER = "/api/users/by-gender";
    public static final String GET_USER_BY_EMAIL = "/api/users/by-email";

    @GetMapping(GET_USER_BY_ID)
    public User findById(@PathVariable("id") Integer id) {

        return userService.findById(id);
    }

    @PostMapping(CREATE_USER)
    public String createUser(User user) {
        UserDto userDto = userDtoFactory.makeUserDto(userService.saveUser(user));
        log.info("user with id={}, was created", userDto.getId());
        return "User wae created.\nDetails:\n" + userDto;
    }

    @GetMapping(GET_ALL_USERS)
    public List<UserDto> getAll() {
        return userService.findAll()
                .stream()
                .map(userDtoFactory::makeUserDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping(DELETE_BY_ID)
    public String deleteById(@PathVariable("id") Integer id) {
        userService.deleteUserById(id);
        log.info("User with id={}, was deleted", id);
        return "User with id = " + id + " was deleted";
    }

    @PostMapping(UPDATE_USER_BY_ID)
    public String updateUser(@PathVariable("id") Integer id, @RequestParam User user) {
        User anotherUser = userService.findFirstById(id).orElseThrow(() -> new BadRequestException(String.format("User with id=%d, not exist", id)));
        if (user.getId().equals(anotherUser.getId())) {
            userService.saveUser(user);
            log.info("user with id ={}, was updated",id);
            return "User with id= " + user.getId() + " was updated";
        } else {
            log.error("User not exist");
            return String.format("User with id=%d, not exist", id);
        }


    }

    @GetMapping(GET_USER_BY_GENDER)
    public List<UserDto> findUsersByGender(@RequestParam("gender") String gender) {
        return userService.findByGender(gender)
                .stream()
                .map(userDtoFactory::makeUserDto)
                .collect(Collectors.toList());
    }

    @GetMapping(GET_USER_BY_EMAIL)
    public UserDto findByEmail(@RequestParam("email") String email) {
        User user = userService.findByEmail(email).orElseThrow(() -> new BadRequestException("User with email " + email + " not exist"));
        return userDtoFactory.makeUserDto(user);
    }
}
