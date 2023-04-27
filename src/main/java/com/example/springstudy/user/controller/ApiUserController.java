package com.example.springstudy.user.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.springstudy.notice.entity.Notice;
import com.example.springstudy.notice.entity.NoticeLike;
import com.example.springstudy.notice.model.NoticeResponse;
import com.example.springstudy.notice.model.ResponseError;
import com.example.springstudy.notice.repository.NoticeLikeRepository;
import com.example.springstudy.notice.repository.NoticeRepository;
import com.example.springstudy.user.entity.User;
import com.example.springstudy.user.exception.ExistsEmailException;
import com.example.springstudy.user.exception.PasswordNotMatchException;
import com.example.springstudy.user.exception.UserNotFoundException;
import com.example.springstudy.user.model.UserInput;
import com.example.springstudy.user.model.UserInputFind;
import com.example.springstudy.user.model.UserInputPassword;
import com.example.springstudy.user.model.UserLogin;
import com.example.springstudy.user.model.UserLoginToken;
import com.example.springstudy.user.model.UserResponse;
import com.example.springstudy.user.model.UserUpdate;
import com.example.springstudy.user.repository.UserRepository;
import com.example.springstudy.util.PasswordUtils;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
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
public class ApiUserController {

    private final UserRepository userRepository;
    private final NoticeRepository noticeRepository;
    private final NoticeLikeRepository noticeLikeRepository;

    /**
     * 31. 사용자 등록시 입력값이 유효하지 않은 경우 예외를 발생시키는 기능을 작성해 보기
     * - 입력값: 이메일(ID), 이름, 비밀번호, 연락처
     * - 사용자 정의 에러 모델을 이용하여 에러를 리턴
     */

//    @PostMapping("/api/user")
//    public ResponseEntity<?> addUser(@RequestBody @Valid UserInput userInput, Errors errors) {
//
//        List<ResponseError> responseErrorList = new ArrayList<>();
//
//        if (errors.hasErrors()) {
//            errors.getAllErrors().forEach((e) -> {
//                responseErrorList.add(ResponseError.of((FieldError) e));
//            });
//
//            return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
//        }
//
//        return ResponseEntity.ok().build();
//    }

    /**
     * 32 사용자 정보를 입력받아서 저장하는 API를 작성해 보기
     * - 입력값: 이메일(유일한 값 확인), 이름, 비밀번호, 연락처, 가입일(현재일시)
     */
//    @PostMapping("/api/user")
//    public ResponseEntity<?> addUser(@RequestBody @Valid UserInput userInput, Errors errors) {
//
//        List<ResponseError> responseErrorList = new ArrayList<>();
//
//        if (errors.hasErrors()) {
//            errors.getAllErrors().forEach((e) -> {
//                responseErrorList.add(ResponseError.of((FieldError) e));
//            });
//
//            return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
//        }
//
//        User user = User.builder()
//                        .email(userInput.getEmail())
//                        .userName(userInput.getUserName())
//                        .password(userInput.getPassword())
//                        .phone(userInput.getPhone())
//                        .regDate(LocalDateTime.now())
//                        .build();
//
//        userRepository.save(user);
//
//        return ResponseEntity.ok().build();
//    }

    /**
     * 33. 사용자 수정를 수정하는 API를 다음 조건에 맞게 작성해 보기
     * - 사용자 정보가 없는 경우 UserNotFoundExecption 발생
     * - 에러메시지는 "사용자 정보가 없습니다."
     * - 수정정보는 연략처만 수정가능, 수정일자는 현재 날짜
     */

    @PutMapping("/api/user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id,
        @RequestBody @Valid UserUpdate userUpdate, Errors errors) {

        List<ResponseError> responseErrorList = new ArrayList<>();
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach((e) -> {
                responseErrorList.add(ResponseError.of((FieldError) e));
            });

            return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));

        user.setPhone(userUpdate.getPhone());
        user.setUpdateDate(LocalDateTime.now());
        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> UserNotFoundExceptionHandler(UserNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * 34. 사용자 정보 조회(가입한 아이디에 대한)의 기능을 수행하는 API를 작성해 보기
     * 다만, 보안상 비밀번호와 가입일, 회원정보 수정일은 내리지 않는다.
     */

    @GetMapping("/api/user/{id}")
    public UserResponse getUser(@PathVariable Long id) {

        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));

        // UserResponse userResponse = new UserResponse(user);

        UserResponse userResponse = UserResponse.of(user);

        return userResponse;
    }

    /**
     * 35. 내가 작성한 공지사항 목록에 대한 API를 작성해 보기
     * 삭제일과 삭제자 아이디는 보안상 내리지 않음
     * 작성자정보를 모두 내리지 않고, 작성자의 아이디와 이름만 내림
     */
    @GetMapping("/api/user/{id}/notice")
    public List<NoticeResponse> UserNotice(@PathVariable Long id) {

        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));

        List<Notice> noticeList = noticeRepository.findByUser(user);

        List<NoticeResponse> noticeResponseList = new ArrayList<>();

        noticeList.stream().forEach((e) -> {
            noticeResponseList.add(NoticeResponse.of(e));
        });

        return noticeResponseList;
    }

    /**
     * 36. 사용자 등록시 이미 존재하는 이메일(이메일은 유일)인 경우 예외를 발생시키는 API를 작성해 보기
     * - 동일한 이메일에 가입된 회원정보가 존재하는 경우 ExsitsEmailException 발생
     */

//    @PostMapping("/api/user")
//    public ResponseEntity<?> addUser(@RequestBody @Valid UserInput userInput, Errors errors) {
//
//        List<ResponseError> responseErrorList = new ArrayList<>();
//        if (errors.hasErrors()) {
//            errors.getAllErrors().stream().forEach((e) -> {
//                responseErrorList.add(ResponseError.of((FieldError)e));
//            });
//            return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
//        }
//
//        if (userRepository.countByEmail(userInput.getEmail()) > 0) {
//            throw new ExistsEmailException("이미 존재하는 이메일입니다.");
//        }
//
//        User user = User.builder()
//                        .email(userInput.getUserName())
//                        .userName(userInput.getUserName())
//                        .phone(userInput.getPhone())
//                        .password(userInput.getPassword())
//                        .regDate(LocalDateTime.now())
//                        .build();
//
//        userRepository.save(user);
//
//        return ResponseEntity.ok().build();
//    }

    @ExceptionHandler(value = {ExistsEmailException.class, PasswordNotMatchException.class})
    public ResponseEntity<?> ExistsEmailExceptionHandler(RuntimeException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * 37. 사용자 비밀번호를 수정하는 API를 작성해 보기
     * - 이전비밀번호와 일치하는 경우 수정
     * - 일치하지 않는경우 PasswordNotMatchException 발생
     * - 발생메시지는 "비밀번호가 일치하지 않습니다."
     */

    @PatchMapping("/api/user/{id}/password")
    public ResponseEntity<?> updateUserPassword(@PathVariable Long id,
        @RequestBody UserInputPassword userInputPassword, Errors errors) {

        List<ResponseError> responseErrorList = new ArrayList<>();
        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach((e) -> {
                responseErrorList.add(ResponseError.of((FieldError)e));
            });
            return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.findByIdAndPassword(id, userInputPassword.getPassword())
            .orElseThrow(() -> new PasswordNotMatchException("비밀번호가 일치하지 않습니다"));

        user.setPassword(userInputPassword.getNewPassword());

        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    /**
     * 38. 회원가입시 비밀번호를 암호화하여 저장하는 API를 작성해 보기
     */

    private String getEncryptPassword(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);
    }

    @PostMapping("/api/user")
    public ResponseEntity<?> addUser(@RequestBody @Valid UserInput userInput, Errors errors) {

        List<ResponseError> responseErrorList = new ArrayList<>();
        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach((e) -> {
                responseErrorList.add(ResponseError.of((FieldError)e));
            });
            return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
        }

        if (userRepository.countByEmail(userInput.getEmail()) > 0) {
            throw new ExistsEmailException("이미 존재하는 이메일입니다.");
        }

        String encryptPassword = getEncryptPassword(userInput.getPassword());

        User user = User.builder()
                        .email(userInput.getEmail())
                        .userName(userInput.getUserName())
                        .phone(userInput.getPhone())
                        .password(encryptPassword)
                        .regDate(LocalDateTime.now())
                        .build();

        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    /**
     * 39. 사용자 회원 탈퇴 기능에 대한 API를 작성해 보기
     * - 회원정보가 존재하지 않은 경우 예외처리
     * - 만약, 사용자가 등록한 공지사항이 있는 경우는 회원삭제가 되지 않음
     */

    @DeleteMapping("/api/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {

        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));

        try {
            userRepository.delete(user);
        } catch (DataIntegrityViolationException e) {
            String message = "제약조건에 문제가 발생하였습니다.";
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            String message = "회원 탈퇴 중 문제가 발생하였습니다.";
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok().build();
    }

    /**
     * 40. 사용자 아이디(이메일)를 찾는 API를 작성해 보기
     * - 이름과 전화번호에 해당하는 이메일을 찾는다.
     */

    @GetMapping("/api/user")
    public ResponseEntity<?> findUser(@RequestBody UserInputFind userInputFind) {

        User user = userRepository.findByUserNameAndPhone(
            userInputFind.getUserName(), userInputFind.getPhone())
            .orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));

        UserResponse userResponse = UserResponse.of(user);

        return ResponseEntity.ok().body(userResponse);
    }

    /**
     * 41. 사용자 비밀번호 초기화 요청(아이디입력 후 전화번호로 문자 전송받음)의 기능을 수행하는 API를 작성해 보기
     * - 아이디에 대한 정보 조회후
     * - 비밀번호를 초기화한 이후에 이를 문자 전송 로직 호출
     * - 초기화 비밀번호는 문자열 10자로 설정함
     */

    private String getResetPassword() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
    }

    @GetMapping("/api/user/{id}/password/reset")
    public ResponseEntity<?> resetUserPassword(@PathVariable Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));

        // 비밀번호 초기화
        String resetPassword = getResetPassword();
        String resetEncryptPassword = getEncryptPassword(resetPassword);
        user.setPassword(resetEncryptPassword);
        userRepository.save(user);

        String message = String.format("[%s]님의 임시 비밀번호가 %s로 초기화 되었습니다."
                , user.getUserName()
                , resetPassword);
        sendSMS(message);

        return ResponseEntity.ok().build();
    }

    void sendSMS(String message) {
        System.out.println("[문자메시지전송]");
        System.out.println(message);
    }

    /**
     * 42. 내가 좋아요한 공지사항을 보는 API를 작성해 보기
     */

    @GetMapping("/api/user/{id}/notice/like")
    public List<NoticeLike> likeNotice(@PathVariable Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));

        List<NoticeLike> noticeLikeList = noticeLikeRepository.findByUser(user);

        return noticeLikeList;
    }

    /**
     * 43. 사용자 이메일과 비밀번호를 통해서 JWT을 발행하는 API를 작성해 보기
     * [조건]
     * - JWT 토큰발행시 사용자 정보가 유효하지 않을때 예외 발생
     * - 사용자정보가 존재하지 않는경우(UserNotFoundException) 에 대해서 예외 발생
     * - 비밀번호가 일치하지 않는 경우(PasswordNotMatchException) 에 대해서 예외 발생
     */

//    @PostMapping("/api/user/login")
//    public ResponseEntity<?> createToken(@RequestBody @Valid UserLogin userLogin, Errors errors) {
//
//        List<ResponseError> responseErrorList = new ArrayList<>();
//        if (errors.hasErrors()) {
//            errors.getAllErrors().stream().forEach((e) -> {
//                responseErrorList.add(ResponseError.of((FieldError)e));
//            });
//            return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
//        }
//
//        User user = userRepository.findByEmail(userLogin.getEmail())
//            .orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));
//
//        if (!PasswordUtils.equalPassword(userLogin.getPassword(), user.getPassword())) {
//            throw new PasswordNotMatchException("비밀번호가 일치하지 않습니다.");
//        }
//
//        return ResponseEntity.ok().build();
//    }

    /**
     * 44. 사용자의 이메일과 비밀번호를 통해서 JWT을 발행하는 로직을 작성해 보기
     * - JWT 토큰발행
     */

//    @PostMapping("/api/user/login")
//    public ResponseEntity<?> createToken(@RequestBody @Valid UserLogin userLogin, Errors errors) {
//
//        List<ResponseError> responseErrorList = new ArrayList<>();
//        if (errors.hasErrors()) {
//            errors.getAllErrors().stream().forEach((e) -> {
//                responseErrorList.add(ResponseError.of((FieldError)e));
//            });
//            return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
//        }
//
//        User user = userRepository.findByEmail(userLogin.getEmail())
//            .orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));
//
//        if (!PasswordUtils.equalPassword(userLogin.getPassword(), user.getPassword())) {
//            throw new PasswordNotMatchException("비밀번호가 일치하지 않습니다.");
//        }
//
//        // 토큰발행시점
//        String token = JWT.create()
//            .withExpiresAt(new Date())
//            .withClaim("user_id", user.getId())
//            .withSubject(user.getUserName())
//            .withIssuer(user.getEmail())
//            .sign(Algorithm.HMAC512("Goldcampus".getBytes()));
//
//        return ResponseEntity.ok().body(UserLoginToken.builder().token(token).build());
//    }


    /**
     * 45. JWT 토큰 발행시 발행 유효기간을 1개월로 저장하는 API를 작성해 보기
     */
    @PostMapping("/api/user/login")
    public ResponseEntity<?> createToken(@RequestBody @Valid UserLogin userLogin, Errors errors) {

        List<ResponseError> responseErrorList = new ArrayList<>();
        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach((e) -> {
                responseErrorList.add(ResponseError.of((FieldError)e));
            });
            return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.findByEmail(userLogin.getEmail())
            .orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));

        if (!PasswordUtils.equalPassword(userLogin.getPassword(), user.getPassword())) {
            throw new PasswordNotMatchException("비밀번호가 일치하지 않습니다.");
        }

        LocalDateTime expiredDateTime = LocalDateTime.now().plusMonths(1);
        Date expiredDate = java.sql.Timestamp.valueOf(expiredDateTime);

        String token = JWT.create()
            .withExpiresAt(expiredDate)
            .withClaim("user_id", user.getId())
            .withSubject(user.getUserName())
            .withIssuer(user.getEmail())
            .sign(Algorithm.HMAC512("Goldcampus".getBytes()));

        return ResponseEntity.ok().body(UserLoginToken.builder().token(token).build());
    }

}
