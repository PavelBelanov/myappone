package re.belanov.myappone.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import re.belanov.myappone.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    UserService userServiceTest = Mockito.mock(UserService.class);
    User user = Mockito.mock(User.class);
//    static User user = new User("Name","lastName","email@email.ru", Set.of(Role.USER),"Male");

    @Test
    void findById() {
        Mockito.when(userServiceTest.findById(user.getId())).thenReturn(Optional.of(user));
    }

    @Test
    void findAll() {
        List<User> data = new ArrayList<>();
        data.add(user);
        Mockito.when(userServiceTest.findAll()).thenReturn(data);
    }

    @Test
    void saveUser() {
        Mockito.when(userServiceTest.saveUser(user)).thenReturn(user);
    }

    @Test
    void deleteUserById() {

    }
}