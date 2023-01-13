package re.belanov.myappone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import re.belanov.myappone.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
