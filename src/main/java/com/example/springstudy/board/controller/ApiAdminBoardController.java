package com.example.springstudy.board.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.springstudy.board.entity.BoardBadReport;
import com.example.springstudy.board.entity.BoardType;
import com.example.springstudy.board.model.BoardBadReportInput;
import com.example.springstudy.board.model.BoardPeriod;
import com.example.springstudy.board.model.BoardTypeCount;
import com.example.springstudy.board.model.BoardTypeInput;
import com.example.springstudy.board.model.BoardTypeUsing;
import com.example.springstudy.board.model.ServiceResult;
import com.example.springstudy.board.service.BoardService;
import com.example.springstudy.common.model.ResponseResult;
import com.example.springstudy.notice.model.ResponseError;
import com.example.springstudy.user.model.ResponseMessage;
import com.example.springstudy.util.JWTUtils;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ApiAdminBoardController {

    private final BoardService boardService;

    /**
     * 74. 게시글의 신고하기 목록을 조회하는 API를 작성해 보기
     */

    @GetMapping("/api/admin/board/badreport")
    public ResponseEntity<?> badReport() {

        List<BoardBadReport> list = boardService.badReportList();
        return ResponseResult.success(list);

    }

}
