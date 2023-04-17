package com.example.springstudy.notice.exception;

public class DuplicateNoticeException extends RuntimeException {
    public DuplicateNoticeException(String message) {
        super(message);
    }
}
