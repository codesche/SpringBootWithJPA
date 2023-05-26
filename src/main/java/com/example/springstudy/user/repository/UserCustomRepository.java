package com.example.springstudy.user.repository;

import com.example.springstudy.user.model.UserLogCount;
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

    public List<UserLogCount> findUserLogCount() {
        String sql = " select u.id, u.email, u.userName "
            + ", (select count(*) from notice n where n.user_id = u.id) notice_count "
            + ", (select count(*) from notice_like nl where nl.user_id = u.id) notice_like_count "
            + "from user u ";

        List<UserLogCount> list = entityManager.createNativeQuery(sql).getResultList();

        return list;
    }

    public List<UserLogCount> findUserLikeBest() {
        String sql = "" +
            " select t1.id, t1.email, t1.user_name, t1.notice_like_count " +
            " from " +
            " ( " +
            "   select u.*, (select count(*) from notice_like nl where nl.user_id = u.id) as notice_like_count " +
            "   from user u" +
            "  ) t1" +
            " order by t1.notice_like_count desc";

        List<UserLogCount> list = entityManager.createNativeQuery(sql).getResultList();

        return list;
    }
}
