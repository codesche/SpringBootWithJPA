package com.example.springstudy.board.repository;

import com.example.springstudy.board.entity.BoardBadReport;
import com.example.springstudy.board.entity.BoardScrap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardScraptRepository extends JpaRepository<BoardScrap, Long> {


}
