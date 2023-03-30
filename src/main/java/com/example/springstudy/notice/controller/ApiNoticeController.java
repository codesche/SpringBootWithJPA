package com.example.springstudy.notice.controller;

import com.example.springstudy.notice.model.NoticeModel;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiNoticeController {

    /**
     * 6. 공지사항 게시판의 목록에 대한 요청을 처리하는 API를 만들어 보기
     * [조건]
     * - REST API 형식으로 구현
     * - HTTP METHOD 는 GET
     * - 요청 주소는 "/api/notice"
     * - 리턴값은 문자열 "공지사항입니다." 리턴
     */
//    @GetMapping("/api/notice")
//    public String noticeString() {
//        return "공지사항입니다.";
//    }

    /**
     * 7. 공지사항 게시판의 목록에 대한 요청을 처리하는 API를 만들어 보기
     * [조건]
     * - REST API 형식으로 구현
     * - HTTP METHOD 는 GET
     * - 요청 주소는 "/api/notice"
     * - 리턴값은 공지사항 게시판의 내용을 추상화한 모델(게시글ID, 제목, 내용, 등록일)이며 데이터는 아래 내용 리턴
     *   게시글ID = 1, 제목 = 공지사항입니다, 내용 = 공지사항 내용입니다, 등록일 = 2021-1-31
     */

//    @GetMapping("/api/notice")
//    public NoticeModel notice() {
//        LocalDateTime regDate = LocalDateTime.of(2021, 2, 8, 0, 0);
//
//        NoticeModel notice = new NoticeModel();
//        notice.setId(1);
//        notice.setTitle("공지사항입니다.");
//        notice.setContents("공지사항 내용입니다.");
//        notice.setRegDt(regDate);
//
//        return notice;
//    }

    /**
     * 8. 공지사항 게시판의 목록에 대한 요청을 처리하는 API를 만들어 보기
     * [조건]
     * - REST API 형식으로 구현
     * - HTTP METHOD 는 GET
     * - 요청 주소는 "/api/notice"
     * - 리턴값은 공지사항 게시판의 내용을 추상화한 모델(게시글ID, 제목, 내용, 등록일)이며 복수형태의 데이터를 리턴하면 데이터는 아래 내용 리턴
     *   게시글ID = 1, 제목 = 공지사항입니다, 내용 = 공지사항 내용입니다, 등록일 = 2021-1-30
     *   게시글ID = 2, 제목 = 두번째 공지사항입니다, 내용 = 두번째 공지사항 내용입니다, 등록일 = 2021-1-31
     */

    @GetMapping("/api/notice")
    public List<NoticeModel> notice() {
        List<NoticeModel> noticeList = new ArrayList<>();

        noticeList.add(NoticeModel.builder()
                    .id(1)
                    .title("공지사항입니다.")
                    .contents("공지사항 내용입니다.")
                    .regDt(LocalDateTime.of(2021, 1, 30, 0, 0))
                    .build());

        noticeList.add(NoticeModel.builder()
                    .id(2)
                    .title("두번째 공지사항입니다.")
                    .contents("두번째 공지사항 내용입니다.")
                    .regDt(LocalDateTime.of(2021, 1, 31, 0, 0))
                    .build());

        return noticeList;
    }

}
