package com.example.springstudy.user.service;

import com.example.springstudy.user.model.UserStatus;
import com.example.springstudy.user.model.UserSummary;
import com.example.springstudy.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserSummary getUserStatusCount() {

        long usingUserCount = userRepository.countByStatus(UserStatus.Using);
        long stopUserCount = userRepository.countByStatus(UserStatus.Stop);
        long totalUserCount = userRepository.count();

        return UserSummary.builder()
                        .usingUserCount(usingUserCount)
                        .stopUserCount(stopUserCount)
                        .totalUserCount(totalUserCount)
                        .build();
    }

}
