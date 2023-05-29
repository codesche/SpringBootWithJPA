package com.example.springstudy.board.repository;

import com.example.springstudy.board.entity.Board;
import com.example.springstudy.board.entity.BoardType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

    long countByBoardType(BoardType boardType);
}
