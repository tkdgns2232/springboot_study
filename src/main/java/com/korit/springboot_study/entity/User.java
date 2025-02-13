package com.korit.springboot_study.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    private int isAccountNonExpired;
    private int isAccountNonLocked;
    private int isCredentialsNonExpired;
    private int isEnabled;
    // db에서 받아 올꺼라 int 사용

    // 여러개의 userRole이 필요
    List<UserRole> userRoles;



}
