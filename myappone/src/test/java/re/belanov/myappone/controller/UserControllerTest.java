package re.belanov.myappone.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import re.belanov.myappone.converters.UserDtoConverter;
import re.belanov.myappone.dto.UserDto;
import re.belanov.myappone.model.User;
import re.belanov.myappone.service.UserService;

import java.util.Objects;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    private final static Integer ID = 1;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;
    @Mock
    private UserDtoConverter userDtoConverter;

    @Test
    void findById() {

    }

//    @Test
//    void createUser() {
//        final User user = mock(User.class);
//        userController.createUser(user);
//        verify(userService).saveUser(user);
//    }

    @Test
    void getAll() {
        userController.getAll();
        verify(userService).findAll();
    }

    @Test
    void deleteById() {
        userController.deleteById(ID);
        verify(userService).deleteUserById(ID);
    }

//    @Test
//    void updateUser() {
//        final User user = mock(User.class);
//        when(userService.findById(ID)).thenReturn(user);
//        final User actual = userController.findUserById(ID);
//        Assertions.assertNotNull(actual);
//        Assertions.assertEquals(user, actual);
//        verify(userService).findById(ID);
//    }
}