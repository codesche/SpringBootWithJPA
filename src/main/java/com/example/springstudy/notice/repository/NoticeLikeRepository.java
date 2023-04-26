package com.example.springstudy.notice.repository;

import com.example.springstudy.notice.entity.NoticeLike;
import com.example.springstudy.user.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeLikeRepository extends JpaRepository<NoticeLike, Long> {

    List<NoticeLike> findByUser(User user);

}
