package com.example.springstudy.user.repository;

import com.example.springstudy.user.entity.User;
import com.example.springstudy.user.entity.UserLoginHistory;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginHistoryRepository extends JpaRepository<UserLoginHistory, Long> {

}
