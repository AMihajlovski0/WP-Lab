package mk.finki.ukim.lab.repository;

import mk.finki.ukim.lab.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
