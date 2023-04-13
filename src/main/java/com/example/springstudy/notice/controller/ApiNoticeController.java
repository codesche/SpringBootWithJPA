package com.example.springstudy.notice.controller;

import com.example.springstudy.notice.entity.Notice;
import com.example.springstudy.notice.exception.NoticeNotFoundException;
import com.example.springstudy.notice.model.NoticeInput;
import com.example.springstudy.notice.model.NoticeModel;
import com.example.springstudy.notice.repository.NoticeRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class ApiNoticeController {

    private final NoticeRepository noticeRepository;

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

//    @GetMapping("/api/notice")
//    public List<NoticeModel> notice() {
//        List<NoticeModel> noticeList = new ArrayList<>();
//
//        noticeList.add(NoticeModel.builder()
//                    .id(1)
//                    .title("공지사항입니다.")
//                    .contents("공지사항 내용입니다.")
//                    .regDt(LocalDateTime.of(2021, 1, 30, 0, 0))
//                    .build());
//
//        noticeList.add(NoticeModel.builder()
//                    .id(2)
//                    .title("두번째 공지사항입니다.")
//                    .contents("두번째 공지사항 내용입니다.")
//                    .regDt(LocalDateTime.of(2021, 1, 31, 0, 0))
//                    .build());
//
//        return noticeList;
//    }

    /**
     * 9. 공지사항 게시판의 목록에 대한 요청을 처리하는 API를 만들어 보기
     * [조건]
     * - REST API 형식으로 구현
     * - HTTP METHOD 는 GET
     * - 요청 주소는 "/api/notice"
     * - 리턴값은 공지사항 게시판의 내용을 추상화한 모델(게시글ID, 제목, 내용, 등록일)이며 복수형태의 데이터를 리턴
     */

    @GetMapping("/api/notice")
    public List<NoticeModel> notice() {

        List<NoticeModel> noticeList = new ArrayList<>();

        return noticeList;
    }

    /**
     * 10. 공지사항 게시판의 목록 중 전체 개수 정보에 대한 요청을 처리하는 API를 만들어 보기
     * [조건]
     * - REST API 형식으로 구현
     * - HTTP METHOD 는 GET
     * - 요청 주소는 "/api/notice/count"
     * - 리턴값은 게시판의 게시글 개수(정수)를 리턴
     *   [확인사항]
     * - 컨트롤러에서 정수형을 리턴하였더라도 클라이언트쪽에 내려가는 부분은 문자열임
     */
    @GetMapping("/api/notice/count")
    public String noticeCount() {
        return "20";
    }

    /**
     * 11. 공지사항에 글을 등록하기 위해서 글작성에 대한 API를 만들어 보기
     * [조건]
     * - REST API 형식으로 구현
     * - HTTP METHOD 는 POST
     * - 요청 주소는 "/api/notice"
     * - 전달되는 파라미터는 x-www-form-urlencoded 형식의 제목, 내용을 입력 받음
     * - 파라미터는 추상화하지 않고 기본데이터 타입 형태로 전달받음
     * - 리턴값은 입력된 형태에 게시글ID(1)를 추가하여 모델 형태로 리턴
     */

//    @PostMapping("/api/notice")
//    public NoticeModel addNotice(@RequestParam String title, @RequestParam String contents) {
//
//        NoticeModel notice = NoticeModel.builder()
//                                        .id(1)
//                                        .title(title)
//                                        .contents(contents)
//                                        .regDt(LocalDateTime.now())
//                                        .build();
//
//        return notice;
//    }

    /**
     * 12. 공지사항에 글을 등록하기 위해서 글작성에 대한 API를 만들어 보기.
     * [조건]
     * - REST API 형식으로 구현
     * - HTTP METHOD 는 POST
     * - 요청 주소는 "/api/notice"
     * - 전달되는 파라미터는 x-www-form-urlencoded 형식의 제목, 내용을 입력 받음
     * - 파라미터를 공지사항 모델로 추상화하여 전달받음
     * - 리턴값은 입력된 형태에 게시글ID(2)과 등록일자(현재시간)을 추가하여 모델 형태로 리턴
     */

//    @PostMapping("/api/notice")
//    public NoticeModel addNotice(NoticeModel noticeModel) {
//        noticeModel.setId(2);
//        noticeModel.setRegDt(LocalDateTime.now());
//
//        return noticeModel;
//    }

    /**
     * 13. 공지사항에 글을 등록하기 위해서 글작성에 대한 API를 만들어 보세요.
     * [조건]
     * - REST API 형식으로 구현
     * - HTTP METHOD 는 POST
     * - 요청 주소는 "/api/notice"
     * - 전달되는 파라미터는 application/json 형식의 제목, 내용을 입력 받음
     * - 파라미터를 공지사항 모델로 추상화하여 전달받음
     * - 리턴값은 입력된 형태에 게시글ID(3)과 등록일자(현재시간)을 추가하여 모델 형태로 리턴
     */

//    @PostMapping("/api/notice")
//    public NoticeModel addNotice(@RequestBody NoticeModel noticeModel) {
//
//        noticeModel.setId(3);
//        noticeModel.setRegDt(LocalDateTime.now());
//
//        return noticeModel;
//    }

    /**
     * 14. 공지사항에 글을 등록하기 위한 글작성에 대한 API를 만들어 보기.
     * [조건]
     * - REST API 형식으로 구현
     * - HTTP METHOD 는 POST
     * - 요청 주소는 "/api/notice"
     * - 전달되는 값은 application/json 형식의 제목, 내용을 입력 받음
     * - 전달된 값을 저장하기 위한 JPA Repository 와 Entity를 통해서 Database 에 저장
     * - 리턴값은 저장된 id값이 포함된 Entity 리턴
     *   [이미설정되어 있는 부분]
     *   -h2db memorydb
     */

//    @PostMapping("/api/notice")
//    public Notice addNotice(@RequestBody NoticeInput noticeInput) {
//
//        Notice notice = Notice.builder()
//            .title(noticeInput.getTitle())
//            .contents(noticeInput.getContents())
//            .regDate(LocalDateTime.now())
//            .build();
//
//        noticeRepository.save(notice);
//
//        return notice;
//    }

    /**
     * 15.  공지사항에 글을 등록하기 위한 글작성에 대한 API를 만들어 보기.
     * [조건]
     * - REST API 형식으로 구현
     * - HTTP METHOD 는 POST
     * - 요청 주소는 "/api/notice"
     * - 전달되는 값은 application/json 형식의 제목, 내용을 입력 받음
     * - 공지사항 등록일은 현재시간을 저장, 공지사항 조회수와 좋아요수는 초기값을 0으로 설정
     * - 전달된 값을 저장하기 위한 JPA Repository 와 Entity를 통해서 Database 에 저장
     * - 리턴값은 저장된 id값이 포함된 Entity 리턴
     *   [이미설정되어 있는 부분]
     *   -h2db memorydb
     */

    @PostMapping("/api/notice")
    public Notice addNotice(@RequestBody NoticeInput noticeInput) {

        Notice notice = Notice.builder()
                            .title(noticeInput.getTitle())
                            .contents(noticeInput.getContents())
                            .regDate(LocalDateTime.now())
                            .hits(0)
                            .likes(0)
                            .build();

        Notice resultNotice = noticeRepository.save(notice);

        return resultNotice;
    }

    /**
     * 16. 공지사항에 글을 수정하기 위한 상세정보 요청에 대한 API를 만들어 보기
     * [조건]
     * - REST API 형식으로 구현
     * - HTTP METHOD 는 GET
     * - 요청 주소는 "/api/notice/1" ("1"은 공지사항의 글ID로 동적으로 변함)
     * - Database에 프로그램 실행시 H2DB에 INSERT 되어 있음
     * - 조회된 결과가 있는 경우 Entity 리턴를 없는 경우는 null을 리턴함
     */
    @GetMapping("/api/notice/{id}")
    public Notice notice(@PathVariable Long id) {

        Optional<Notice> notice = noticeRepository.findById(id);
        if (notice.isPresent()) {
            return notice.get();
        }

        return null;
    }

    /**
     * 17. 공지사항에 글을 수정하기 위한 글수정에 대한 API를 만들어 보기
     * [조건]
     * - REST API 형식으로 구현
     * - HTTP METHOD 는 PUT
     * - 요청 주소는 "/api/notice/1" ("1"은 공지사항의 글ID로 동적으로 변함)
     * - 전달되는 값은 application/json 형식의 공지사항 글ID, 제목, 내용을 입력 받음
     * - 공지사항 수정일은 현재시간을 저장, 공지사항 조회수와 좋아요수는 변경하지 않음
     * - 데이터를 수정한은 경우는 Data매핑에 대한 Entity로 필요없는 항목까지 받지 말고 필요한 데이터만 입력받게 작성
     * - 전달된 값을 수정하기 위한 JPA Repository 와 Entity를 통해서 Database 에 수정
     */
//    @PutMapping("/api/notice/{id}")
//    public void updateNotice(@PathVariable Long id, @RequestBody NoticeInput noticeInput) {
//
//        Optional<Notice> notice = noticeRepository.findById(id);
//        if (notice.isPresent()) {
//            notice.get().setTitle(noticeInput.getTitle());
//            notice.get().setContents(noticeInput.getContents());
//            notice.get().setUpdateDate(LocalDateTime.now());
//            noticeRepository.save(notice.get());
//        }
//    }

    /**
     * 18. 공지사항에 글을 수정하기 위한 글수정에 대한 API를 만들어 보기
     * [조건]
     * - REST API 형식으로 구현
     * - HTTP METHOD 는 PUT
     * - 요청 주소는 "/api/notice/1" ("1"은 공지사항의 글ID로 동적으로 변함)
     * - 전달되는 값은 application/json 형식의 공지사항 글ID, 제목, 내용을 입력 받음
     * - 공지사항 수정일은 현재시간을 저장, 공지사항 조회수와 좋아요수는 변경하지 않음
     * - 데이터를 수정한은 경우는 Data매핑에 대한 Entity로 필요없는 항목까지 받지 말고 필요한 데이터만 입력받게 작성
     * - 공지사항의 글이 존재하지 않을 경우 예외사항을 발생시킨다.
     * - 예외처리는 ExceptionHandler를 통해서 구현하고, 발생하는 예외에 대해서는 400, 예외 메세지를 리턴함
     */

    @ExceptionHandler(NoticeNotFoundException.class)
    public ResponseEntity<String> handlerNoticeNotFoundException(NoticeNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

//    @PutMapping("/api/notice/{id}")
//    public void updateNotice(@PathVariable Long id, @RequestBody NoticeInput noticeInput) {
//
//        /*
//        Optional<Notice> notice = noticeRepository.findById(id);
//        if (!notice.isPresent()) {
//            // 예외 발생
//            throw new NoticeNotFoundException("공지사항의 글이 존재하지 않습니다.");
//        }*/
//
//        Notice notice = noticeRepository.findById(id)
//            .orElseThrow(() -> new NoticeNotFoundException("공지사항의 글이 존재하지 않습니다."));
//
//        // 공지사항 글이 있을 때
//        notice.setTitle(noticeInput.getTitle());
//        notice.setContents(noticeInput.getContents());
//        notice.setUpdateDate(LocalDateTime.now());
//        noticeRepository.save(notice);
//    }

    /**
     * 19. 공지사항에 글을 수정하기 위한 글수정에 대한 API를 만들어 보기
     * [조건]
     * - REST API 형식으로 구현
     * - HTTP METHOD 는 PUT
     * - 요청 주소는 "/api/notice/1" ("1"은 공지사항의 글ID로 동적으로 변함)
     * - 전달되는 값은 application/json 형식의 공지사항 글ID, 제목, 내용을 입력 받음
     * - 공지사항 수정일은 현재시간을 저장, 공지사항 조회수와 좋아요수는 변경하지 않음
     * - 데이터를 수정한은 경우는 Data매핑에 대한 Entity로 필요없는 항목까지 받지 말고 필요한 데이터만 입력받게 작성
     * - 데이터 수정일을 추가하여 수정한 날짜/시간도 합께 업데이트를 진행함
     */
    @PutMapping("/api/notice/{id}")
    public void updateNotice(@PathVariable Long id, @RequestBody NoticeInput noticeInput) {

        Notice notice = noticeRepository.findById(id)
            .orElseThrow(() -> new NoticeNotFoundException("공지사항의 글이 존재하지 않습니다."));

        // 공지사항 글이 있을 때
        notice.setTitle(noticeInput.getTitle());
        notice.setContents(noticeInput.getContents());
        notice.setUpdateDate(LocalDateTime.now());
        noticeRepository.save(notice);
    }

    /**
     * 20. 공지사항에 글의 조회수를 증가시키는 API를 만들어 보기
     * [조건]
     * - REST API 형식으로 구현
     * - HTTP METHOD 는 PATCH
     * - 요청 주소는 "/api/notices4/1/hits" ("1"은 공지사항의 글ID로 동적으로 변함)
     */

    @PatchMapping("/api/notice/{id}/hits")
    public void noticeHits(@PathVariable Long id) {

        Notice notice = noticeRepository.findById(id)
            .orElseThrow(() -> new NoticeNotFoundException("공지사항의 글이 존재하지 않습니다."));

        notice.setHits(notice.getHits() + 1);

        noticeRepository.save(notice);
    }


}
