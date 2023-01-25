package re.belanov.myappone.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import re.belanov.myappone.model.User;
import re.belanov.myappone.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public User findById(Integer id){
        return userRepository.findById(id).orElse(null);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public void deleteUserById(Integer id){
        userRepository.deleteById(id);
    }

    public List<User> findByGender(String gender){
       return userRepository.findUserByGender(gender);
    }

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmailIgnoreCase(email);
    }
}
