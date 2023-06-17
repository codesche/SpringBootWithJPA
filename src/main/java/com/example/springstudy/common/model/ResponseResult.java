package com.example.springstudy.common.model;

import com.example.springstudy.board.entity.BoardBadReport;
import com.example.springstudy.board.model.ServiceResult;
import com.example.springstudy.user.model.ResponseMessage;
import java.util.List;
import org.springframework.http.ResponseEntity;

public class ResponseResult {

    public static ResponseEntity<?> fail(String message) {
        return ResponseEntity.badRequest().body(ResponseMessage.fail(message));
    }

    public static ResponseEntity<?> success() {
        return success(null);
    }

    public static ResponseEntity<?> success(List<BoardBadReport> data) {
        return ResponseEntity.ok().body(ResponseMessage.success(data));
    }

    public static ResponseEntity<?> result(ServiceResult result) {
        if (result.isFail()) {
            return ResponseResult.fail(result.getMessage());
        }
        return success();
    }
}
