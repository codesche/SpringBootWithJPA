package com.example.springstudy.user.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdate {

    private long id;

    @Size(min = 10, message = "연락처는 최대 10자까지 입력해야 합니다.")
    @NotBlank(message = "연락처는 필수 항목 입니다.")
    private String phone;

}
