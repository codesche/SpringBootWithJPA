package com.example.springstudy.user.repository;

import com.example.springstudy.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    int countByEmail(String email);

    Optional<User> findByIdAndPassword(Long id, String password);

}
