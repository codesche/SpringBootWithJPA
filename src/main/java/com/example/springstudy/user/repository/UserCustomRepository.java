package com.example.springstudy.user.repository;

import com.example.springstudy.user.model.UserNoticeCount;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserCustomRepository {

    private final EntityManager entityManager;

    public List<UserNoticeCount> findUserNoticeCount() {

        String sql = " select u.id, u.email, u.userName, "
            + "(select count(*) from Notice n where n.user_id = u.id) notice_count from User u ";

        List<UserNoticeCount> list = entityManager.createNativeQuery(sql).getResultList();
        return list;
    }

}
