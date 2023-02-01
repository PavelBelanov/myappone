package re.belanov.myappone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import re.belanov.myappone.model.User;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findUserByGender(String gender);

    @Query("SELECT u FROM User u WHERE u.email = LOWER(:email)")
    Optional <User> findByEmailIgnoreCase(String email);

    Optional<List<User>> findUsersByLastNameIgnoreCase(String lastName);



}
