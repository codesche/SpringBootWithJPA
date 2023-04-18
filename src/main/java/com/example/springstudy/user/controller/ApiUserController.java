package com.example.springstudy.user.controller;

import com.example.springstudy.notice.model.ResponseError;
import com.example.springstudy.user.model.UserInput;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiUserController {

    /**
     * 31. 사용자 등록시 입력값이 유효하지 않은 경우 예외를 발생시키는 기능을 작성해 보기
     * - 입력값: 이메일(ID), 이름, 비밀번호, 연락처
     * - 사용자 정의 에러 모델을 이용하여 에러를 리턴
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

        return ResponseEntity.ok().build();
    }

}
