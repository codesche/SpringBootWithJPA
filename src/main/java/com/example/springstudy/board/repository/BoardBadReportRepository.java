package com.example.springstudy.board.repository;

import com.example.springstudy.board.entity.Board;
import com.example.springstudy.board.entity.BoardBadReport;
import com.example.springstudy.board.entity.BoardType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardBadReportRepository extends JpaRepository<BoardBadReport, Long> {


}
