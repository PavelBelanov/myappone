package re.belanov.myappone.controller;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import re.belanov.myappone.dto.UserDto;
import re.belanov.myappone.exceptions.BadRequestException;
import re.belanov.myappone.exceptions.NotFoundException;
import re.belanov.myappone.factories.UserDtoFactory;
import re.belanov.myappone.model.Role;
import re.belanov.myappone.model.User;
import re.belanov.myappone.service.UserService;

import java.util.List;
import java.util.Set;
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

    public static final String EDIT_USER_BY_ID = "/api/update/{id}";
    public static final String GET_USER_BY_GENDER = "/api/users/by-gender";
    public static final String GET_USER_BY_EMAIL = "/api/users/by-email";

    @GetMapping(GET_USER_BY_ID)
    public UserDto findUserById(@PathVariable("id") Integer id) {
        return userDtoFactory.makeUserDto(userService.findById(id)
                .orElseThrow(() -> new BadRequestException(String.format("User with id=%d, not exist", id))));
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

    @PatchMapping(EDIT_USER_BY_ID)
    public String editUser(@PathVariable("id") Integer id, @RequestParam(required = false) String firstName,
                           @RequestParam(required = false)String lastName, @RequestParam(required = false) @Email String email,
                           @RequestParam(required = false) Set<Role> roles, @RequestParam(required = false) String gender) {
        User userWithId = userService.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("User with id=%d doesn't exist", id)));
        userWithId.setFirstName(firstName);
        userWithId.setLastName(lastName);
        userWithId.setEmail(email);
        userWithId.setRoles(roles);
        userWithId.setGender(gender);
        userService.saveUser(userWithId);
        return String.format("User with id=%s updated", id);
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
