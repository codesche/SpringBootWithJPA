package com.example.springstudy.board.repository;

import com.example.springstudy.board.entity.Board;
import com.example.springstudy.board.entity.BoardHits;
import com.example.springstudy.board.entity.BoardLike;
import com.example.springstudy.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {

    long countByBoardAndUser(Board board, User user);

    Optional<BoardLike> findByBoardAndUser(Board board, User user);
}
