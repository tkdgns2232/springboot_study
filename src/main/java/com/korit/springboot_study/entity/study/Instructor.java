package com.korit.springboot_study.entity.study;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 데이터베이스를 만들때는 entity부터 만들기
public class Instructor {
    private int instructorId;
    private String instructorName;
}
