package com.korit.springboot_study.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class ReqAddBookDto {
    @ApiModelProperty(value = "책제목", example = "아이", required = true)
    @Pattern(regexp = "^[a-zA-Z0-9가-힣][a-zA-Z0-9가-힣\\s]*$", message = "이 정규표현식은 첫 글자에 공백을 포함할 수 없습니다.")
    private String bookName;
}
