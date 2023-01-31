package re.belanov.myappone.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import re.belanov.myappone.factories.UserDtoFactory;
import re.belanov.myappone.model.User;
import re.belanov.myappone.service.UserService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    private final static Integer ID = 1;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;
    @Mock
    private UserDtoFactory userDtoFactory;

//    @Test
//    void findById() {
//        final UserDto user = mock(UserDto.class);
//        when(userDtoFactory.makeUserDto(userService.findById(ID))).thenReturn(user);
//        final UserDto actual = userController.findUserById(ID);
//        Assertions.assertNotNull(actual);
//        Assertions.assertEquals(user, actual);
//        verify(userService).findById(ID);
//    }

    @Test
    void createUser() {
        final User user = mock(User.class);
        userController.createUser(user);
        verify(userService).saveUser(user);
    }

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