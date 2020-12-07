package iitu.kz.UserService.Repository;

import iitu.kz.UserService.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> { }
