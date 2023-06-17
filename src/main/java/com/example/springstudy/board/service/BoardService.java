package com.example.springstudy.board.service;

import com.example.springstudy.board.entity.BoardBadReport;
import com.example.springstudy.board.entity.BoardType;
import com.example.springstudy.board.model.BoardBadReportInput;
import com.example.springstudy.board.model.BoardPeriod;
import com.example.springstudy.board.model.BoardTypeCount;
import com.example.springstudy.board.model.BoardTypeInput;
import com.example.springstudy.board.model.BoardTypeUsing;
import com.example.springstudy.board.model.ServiceResult;
import java.util.List;

public interface BoardService {

    ServiceResult addBoard(BoardTypeInput boardTypeInput);

    ServiceResult updateBoard(long id, BoardTypeInput boardTypeInput);

    ServiceResult deleteBoard(Long id);

    List<BoardType> getAllBoardType();

    /**
     * 게시판타입의 사용여부를 설정
     */
    ServiceResult setBoardTypeUsing(Long id, BoardTypeUsing boardTypeUsing);

    /**
     * 게시판타입의 게시글 수를 리턴
     */
    List<BoardTypeCount> getBoardTypeCount();

    /**
     * 게시글을 최상단에 배치
     */
    ServiceResult setBoardTop(Long id, boolean topYn);

    /**
     * 게시글의 게시기간을 설정
     */
    ServiceResult setBoardPeriod(Long id, BoardPeriod boardPeriod);

    /**
     * 게시글의 조회수를 증가
     */
    ServiceResult setBoardHits(Long id, String email);

    /**
     * 게시글의 좋아요
     */
    ServiceResult setBoardLike(Long id, String email);

    /**
     * 게시글의 좋아요를 취소함
     */
    ServiceResult setBoardUnLike(Long id, String email);

    /**
     * 게시글을 신고하는 기능
     */
    ServiceResult addBadReport(Long id, String email, BoardBadReportInput boardBadReportInput);

    /**
     * 신고된 게시글정보 목록
     */
    List<BoardBadReport> badReportList();
}
