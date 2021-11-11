package com.backmin.domains.member.controller;

import static com.backmin.domains.common.enums.ErrorInfo.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.*;

import com.backmin.config.exception.BusinessException;
import com.backmin.domains.common.dto.ApiResult;
import com.backmin.domains.common.enums.ErrorInfo;
import com.backmin.domains.member.dto.request.MemberCreateParam;
import com.backmin.domains.member.dto.request.MemberUpdateParam;
import com.backmin.domains.member.service.MemberService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public ApiResult createMember(@RequestBody @Valid MemberCreateParam memberCreateParam) {
        memberService.save(memberCreateParam);
        return ApiResult.ok();
    }

    @PatchMapping(value = "/{memberId}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public ApiResult updateMember(@PathVariable("memberId") Long memberId, @RequestBody @Valid MemberUpdateParam memberUpdateParam) {
        memberService.update(memberId, memberUpdateParam);
        return ApiResult.ok();
    }

    @GetMapping(value = "/email/{email}", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public ApiResult emailCheck(@PathVariable String email) {
        return ApiResult.ok(memberService.checkMemberEmail(email));
    }

    @GetMapping(value = "/nickname/{nickname}", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public ApiResult nickNameCheck(@PathVariable String nickname) {
        return ApiResult.ok(memberService.checkMemberNickname(nickname));
    }

}
