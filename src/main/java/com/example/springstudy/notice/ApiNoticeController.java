package com.example.springstudy.notice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiNoticeController {

    /**
     * 6. 공지사항 게시판의 목록에 대한 요청을 처리하는 API를 만들어 보세요.
     * [조건]
     * - REST API 형식으로 구현
     * - HTTP METHOD 는 GET
     * - 요청 주소는 "/api/notice"
     * - 리턴값은 문자열 "공지사항입니다." 리턴
     */
    @GetMapping("/api/notice")
    public String noticeString() {
        return "공지사항입니다.";
    }

}
