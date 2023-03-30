package com.example.springstudy.notice.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class NoticeModel {

    /**
     * 7. 공지사항 게시판의 목록에 대한 요청을 처리하는 API를 만들어 보기
     * [조건]
     * - REST API 형식으로 구현
     * - HTTP METHOD 는 GET
     * - 요청 주소는 "/api/notice"
     * - 리턴값은 공지사항 게시판의 내용을 추상화한 모델(게시글ID, 제목, 내용, 등록일)이며 데이터는 아래 내용 리턴
     *   게시글ID = 1, 제목 = 공지사항입니다, 내용 = 공지사항 내용입니다, 등록일 = 2021-1-31
     */

    // ID, 제목, 내용, 등록일
    private int id;
    private String title;
    private String contents;
    private LocalDateTime regDt;

}
