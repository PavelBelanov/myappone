package re.belanov.myappone.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import re.belanov.myappone.model.User;
import re.belanov.myappone.service.UserService;

import java.util.List;

@RestController()
@RequestMapping(value = "/api")
@AllArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/users/{id}")
    public User findById(@PathVariable("id") Integer id) {

        return userService.findById(id);
    }

    @PostMapping("/create")
    public User createUser(User user) {

        return userService.saveUser(user);
    }

    @GetMapping("/users")
    public List<User> getAll() {
        return userService.findAll();
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") Integer id) {
        userService.deleteUserById(id);
        return "User with id = " + id + " was deleted";
    }
    @PostMapping("/update/{id}")
    public String updateUser(User user){
        userService.saveUser(user);
       return "User with id= " + user.getId() + " was updated";
    }

    @GetMapping("/users/by-gender")
    public List<User> findUsersByGender(@RequestParam("gender") String gender){
        return userService.findByGender(gender);
    }
    @GetMapping("/users/by-email")
        public User findByEmail(@RequestParam("email") String email){
        return userService.findByEmail(email).orElse(null);
    }
}
