package com.korit.springboot_study.dto.request.study;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data // dto만들면 @data 사용
@ApiModel(description = "학생정보 조회 학습 DTO")
public class ReqStudentDto {
    @NonNull
    @ApiModelProperty(value = "학생 이름", example = "김준일", required = true)
    private String name;
    @ApiModelProperty(value = "학생 나이", example = "32", required = false)
    private int age;
}
