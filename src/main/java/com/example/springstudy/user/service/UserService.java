package com.example.springstudy.user.service;

import com.example.springstudy.user.entity.User;
import com.example.springstudy.user.model.UserSummary;
import java.util.List;

public interface UserService {

    UserSummary getUserStatusCount();


    List<User> getTodayUsers();
}
