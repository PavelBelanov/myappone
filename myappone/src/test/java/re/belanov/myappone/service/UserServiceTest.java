package re.belanov.myappone.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import re.belanov.myappone.repository.UserRepository;
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repository;
    @InjectMocks
    private UserService userService;

    @Test
    void findById() {
    }

    @Test
    void findAll() {
        userService.findAll();

    }

    @Test
    void saveUser() {
    }

    @Test
    void deleteUserById() {
    }
}