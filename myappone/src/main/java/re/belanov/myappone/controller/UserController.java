package re.belanov.myappone.controller;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import re.belanov.myappone.converters.UserDtoConverter;
import re.belanov.myappone.dto.UserDto;
import re.belanov.myappone.exceptions.BadRequestException;
import re.belanov.myappone.exceptions.NotFoundException;
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

    private final UserDtoConverter userDtoConverter;

    public static final String GET_USER_BY_ID = "/api/users/{id}";
    public static final String CREATE_USER = "/api/create";
    public static final String GET_ALL_USERS = "/api/users";
    public static final String DELETE_BY_ID = "/api/delete/{id}";
    public static final String EDIT_USER_BY_ID = "/api/update/{id}";
    public static final String GET_USER_BY_GENDER = "/api/users/by-gender";
    public static final String GET_USER_BY_EMAIL = "/api/users/by-email";
    public static final String GET_USER_BY_LASTNAME = "/api/users/by-lastname";

    @GetMapping(GET_USER_BY_ID)
    public UserDto findUserById(@PathVariable("id") Integer id) {
        return userDtoConverter.makeUserDto(userService.findById(id)
                .orElseThrow(() -> new BadRequestException(String.format("User with id=%d, not exist", id))));
    }

        @PostMapping(CREATE_USER)
    public UserDto createUser(User user) {
        UserDto userDto = userDtoConverter.makeUserDto(userService.saveUser(user));
        log.info("user with id={}, was created", userDto.getId());
        return userDto;
    }
//    @PostMapping(CREATE_USER)
//    public String createUser(UserDto userDto){
//        User user = userDtoConverter.makeUserFromDto(userDto);
//        user.setRoles(userDto.getRole())     ;
//        userService.saveUser(user);
//        log.info("User was saving");
//        return "Saved";
//    }

    @GetMapping(GET_ALL_USERS)
    public List<UserDto> getAll() {
        return userService.findAll()
                .stream()
                .map(userDtoConverter::makeUserDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping(DELETE_BY_ID)
    public String deleteById(@PathVariable("id") Integer id) {
        User user = userService.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("User with id=%d doesn't exist", id)));
        userService.deleteUserById(user.getId());
        log.info("User with id={}, was deleted", id);
        return "User with id = " + id + " was deleted";
    }

    @PatchMapping(EDIT_USER_BY_ID)
    public UserDto editUser(@PathVariable("id") Integer id,
                           @RequestParam(required = false) String firstName,
                           @RequestParam(required = false) String lastName,
                           @RequestParam(required = false) @Email String email,
                           @RequestParam(required = false) Set<Role> roles,
                           @RequestParam(required = false) String gender) {
        User userWithId = userService.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("User with id=%d doesn't exist", id)));
        userWithId.setFirstName(firstName);
        userWithId.setLastName(lastName);
        userWithId.setEmail(email);
        userWithId.setRoles(roles);
        userWithId.setGender(gender);
        userService.saveUser(userWithId);
        return userDtoConverter.makeUserDto(userWithId);
    }

    @GetMapping(GET_USER_BY_GENDER)
    public List<UserDto> findUsersByGender(@RequestParam("gender") String gender) {
        return userService.findByGender(gender)
                .stream()
                .map(userDtoConverter::makeUserDto)
                .collect(Collectors.toList());
    }

    @GetMapping(GET_USER_BY_EMAIL)
    public UserDto findByEmail(@RequestParam("email") String email) {
        User user = userService.findByEmail(email)
                .orElseThrow(() -> new BadRequestException("User with email " + email + " not exist"));
        return userDtoConverter.makeUserDto(user);
    }

    @GetMapping(GET_USER_BY_LASTNAME)
    public List<UserDto> findByLastName(@RequestParam(value = "lastName", required = false) String lastName){
        List<User> users = userService.findUserByLastName(lastName)
                .orElseThrow(()->new NotFoundException(String.format("User with Lastname %s, not exist", lastName)));
        return users.stream()
                .map(userDtoConverter::makeUserDto)
                .collect(Collectors.toList());
    }
}
