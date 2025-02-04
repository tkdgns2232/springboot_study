package com.korit.springboot_study.dto.response.study;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel(description = "학생 추가 응답 DTO")
public class RespAddStudentDto {
    @ApiModelProperty(value = "응답 메시지", example = "학생 추가 완료")
    private String message;
    @ApiModelProperty(value = "성공 상태", example = "학생 추가 완료 상태 true or false")
    private boolean isSuccess;
}
