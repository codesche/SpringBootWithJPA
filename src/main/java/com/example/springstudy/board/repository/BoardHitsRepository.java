package com.example.springstudy.board.repository;

import com.example.springstudy.board.entity.Board;
import com.example.springstudy.board.entity.BoardHits;
import com.example.springstudy.board.entity.BoardType;
import com.example.springstudy.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardHitsRepository extends JpaRepository<BoardHits, Long> {

    long countByBoardAndUser(Board board, User user);

}
