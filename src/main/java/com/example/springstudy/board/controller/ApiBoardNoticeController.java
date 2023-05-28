package com.example.springstudy.board.controller;

import com.example.springstudy.board.entity.BoardType;
import com.example.springstudy.board.model.BoardTypeInput;
import com.example.springstudy.board.model.ServiceResult;
import com.example.springstudy.board.service.BoardService;
import com.example.springstudy.notice.entity.Notice;
import com.example.springstudy.notice.exception.AlreadyDeletedException;
import com.example.springstudy.notice.exception.DuplicateNoticeException;
import com.example.springstudy.notice.exception.NoticeNotFoundException;
import com.example.springstudy.notice.model.NoticeDeleteInput;
import com.example.springstudy.notice.model.NoticeInput;
import com.example.springstudy.notice.model.NoticeModel;
import com.example.springstudy.notice.model.ResponseError;
import com.example.springstudy.notice.repository.NoticeRepository;
import com.example.springstudy.user.model.ResponseMessage;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ApiBoardNoticeController {

    private final BoardService boardService;

    /**
     * 61. 게시판타입을 추가하는 API를 작성해 보기
     * - 동일한 게시판 제목이 있는 경우 status:200, result:false, message에 이미 동일한 게시판이 존재한다는 메시지 리턴
     * - 게시판이름은 필수항목에 대한 부분 체크
     */

    @PostMapping("/api/board/type")
    public ResponseEntity<?> addBoardType(@RequestBody @Valid BoardTypeInput boardTypeInput, Errors errors) {

        if (errors.hasErrors()) {
            List<ResponseError> responseErrors = ResponseError.of(errors.getAllErrors());

            return new ResponseEntity<>(ResponseMessage.fail("입력값이 정확하지 않습니다",
                responseErrors), HttpStatus.BAD_REQUEST);
        }

        ServiceResult result = boardService.addBoard(boardTypeInput);

        if (!result.isResult()) {
            return ResponseEntity.ok().body(ResponseMessage.fail(result.getMessage()));
        }

        return ResponseEntity.ok().build();
    }

    /**
     * 62. 게시판타입명을 수정하는 API를 작성해 보기
     * - 게시판명이 동일할 경우 "수정할 이름이 동일한 게시판명 입니다." 리턴
     */

    @PutMapping("/api/board/type/{id}")
    public ResponseEntity<?> updateBoardType(@PathVariable Long id,
        @RequestBody @Valid BoardTypeInput boardTypeInput, Errors errors) {

        if (errors.hasErrors()) {
            List<ResponseError> responseErrors = ResponseError.of(errors.getAllErrors());

            return new ResponseEntity<>(ResponseMessage.fail("입력값이 정확하지 않습니다",
                responseErrors), HttpStatus.BAD_REQUEST);
        }

        ServiceResult result = boardService.updateBoard(id, boardTypeInput);

        if (!result.isResult()) {
            return ResponseEntity.ok().body(ResponseMessage.fail(result.getMessage()));
        }

        return ResponseEntity.ok().build();
    }


}
