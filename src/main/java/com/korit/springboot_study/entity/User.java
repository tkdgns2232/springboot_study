package com.korit.springboot_study.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int userId;
    private String username;
    @JsonIgnore // 이 필드는 JSON으로 직렬화/역직렬화할 때 무시됨
    private String password;
    private String name;
    private String email;
}
