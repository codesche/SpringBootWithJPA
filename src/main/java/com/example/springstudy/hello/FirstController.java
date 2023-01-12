package com.example.springstudy.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class FirstController {

    /**
     1. 클라이언트 요청에 대한 주소 만들기
     [조건]
     - 컨트롤러 인식을 위한 Controller 어노테이션 이용
     - 주소매핑은 RequestMapping을 이용
     - HTTP 메소드는 GET 방식
     - 리턴값은 아무것도 없음
     - 주소는 "/first-url"
     */
    @RequestMapping(value = "/first-url", method = RequestMethod.GET)
    public void first() {

    }

}
