package com.backmin.domains.member.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class MemberCreateParam {

    @NotBlank(message = "올바른 형식의 이메일을 입력해주세요.")
    @Length(max = 100)
    private String email;

    @NotBlank(message = "올바른 형식의 패스워드를 입력해주세요.")
    @Length(max = 100)
    private String password;

    @NotBlank(message = "올바른 형식의 비밀번호를 입력해주세요.")
    @Length(max = 13)
    private String phoneNumber;

    @NotBlank(message = "올바른 형식의 닉네임을 입력해주세요.")
    @Length(max = 10)
    private String nickName;

    @NotBlank(message = "올바른 형식의 주소를 입력해주세요.")
    @Length(max = 200)
    private String address;

}
