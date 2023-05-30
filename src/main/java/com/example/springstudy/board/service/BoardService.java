package com.example.springstudy.board.service;

import com.example.springstudy.board.entity.BoardType;
import com.example.springstudy.board.model.BoardTypeInput;
import com.example.springstudy.board.model.ServiceResult;
import java.util.List;

public interface BoardService {

    ServiceResult addBoard(BoardTypeInput boardTypeInput);

    ServiceResult updateBoard(long id, BoardTypeInput boardTypeInput);

    ServiceResult deleteBoard(Long id);

    List<BoardType> getAllBoardType();
}
