package com.example.springstudy.notice.repository;

import com.example.springstudy.notice.entity.Notice;
import com.example.springstudy.user.entity.User;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {

    Optional<List<Notice>> findByIdIn(List<Long> idList);

    // 제목동일, 내용동일, 등록시간이 체크시간보다 크다.
    Optional<List<Notice>> findByTitleAndAndContentsAndRegDateIsGreaterThanEqual(String title,
        String contents, LocalDateTime regDate);

    int countByTitleAndContentsAndRegDateIsGreaterThanEqual(String title,
        String contents, LocalDateTime regDate);

    List<Notice> findByUser(User user);

    long countByUser(User user);

}
