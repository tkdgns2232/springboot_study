package com.korit.springboot_study.dto.request.study;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class ReqAddMajorDto {
    @ApiModelProperty(value = "학과명", example = "컴퓨터공학과", required = true)
    @Pattern(regexp = "^[가-힣]+$", message = "이 정규표현식은 학과명이 빈 문자열이거나 공백을 포함하지 않으며, 오직 한글만 포함되는지 확인합니다")
    private String majorName;
    @ApiModelProperty(value = "학과명2", example = "컴퓨터공학과2", required = true)
    @Pattern(regexp = "^[가-힣]+$", message = "이 정규표현식은 학과명이 빈 문자열이거나 공백을 포함하지 않으며, 오직 한글만 포함되는지 확인합니다")
    private String majorName2;
    @ApiModelProperty(value = "학과명3", example = "컴퓨터공학과3", required = true)
    @Pattern(regexp = "^[가-힣]+$", message = "이 정규표현식은 학과명이 빈 문자열이거나 공백을 포함하지 않으며, 오직 한글만 포함되는지 확인합니다")
    private String majorName3;
}
