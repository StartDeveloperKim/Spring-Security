package spring.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.security.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
