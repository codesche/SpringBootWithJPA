package com.example.springstudy.board.repository;

import com.example.springstudy.board.entity.BoardType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardTypeRepository extends JpaRepository<BoardType, Long> {

    BoardType findByBoardName(String name);
}
