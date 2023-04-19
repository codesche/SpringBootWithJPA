package com.example.springstudy.user.controller;

import com.example.springstudy.notice.model.ResponseError;
import com.example.springstudy.user.entity.User;
import com.example.springstudy.user.model.UserInput;
import com.example.springstudy.user.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ApiUserController {

    private final UserRepository userRepository;

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
    @PostMapping("/api/user")
    public ResponseEntity<?> addUser(@RequestBody @Valid UserInput userInput, Errors errors) {

        List<ResponseError> responseErrorList = new ArrayList<>();

        if (errors.hasErrors()) {
            errors.getAllErrors().forEach((e) -> {
                responseErrorList.add(ResponseError.of((FieldError) e));
            });

            return new ResponseEntity<>(responseErrorList, HttpStatus.BAD_REQUEST);
        }

        User user = User.builder()
                        .email(userInput.getEmail())
                        .userName(userInput.getUserName())
                        .password(userInput.getPassword())
                        .phone(userInput.getPhone())
                        .regDate(LocalDateTime.now())
                        .build();

        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

}
