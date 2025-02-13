package com.korit.springboot_study.dto.request;

import com.korit.springboot_study.entity.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
public class ReqSignupDto {


    @ApiModelProperty(value = "사용자이름", example = "user1234", required = true)
    @Pattern(regexp = "^[a-zA-Z0-9_-]{4,16}$", message = "영문자(대소문자), 숫자, 밑줄(_) 또는 하이픈(-)만 사용할 수 있습니다.")
    private String username;

    @ApiModelProperty(value = "비밀번호", example = "User12345678!", required = true)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_\\-+=<>?]).{8,}$", message = "영어 대소문자, 숫자, 특수문자(!@#$%^&*()_\\-+=<>?)를 하나이상 모두 포함하며 8자리 이상 입력해야합니다.")
    private String password;

    @ApiModelProperty(value = "성명", example = "홍길동", required = true)
    @Pattern(regexp = "^[가-힣]{2,}$", message = "한글 2자 이상만 입력가능합니다.")
    private String name;

    @ApiModelProperty(value = "이메일주소", example = "user@mail.com", required = true)
    @Email(message = "이메일 형식으로 입력해야합니다.") //email은 전용 어노테이션이 있다.
    private String email;

    public User toUser(BCryptPasswordEncoder passwordEncoder) {
        return User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .name(name)
                .email(email)
                .build();
    }
}
