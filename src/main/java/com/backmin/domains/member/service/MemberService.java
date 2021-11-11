package com.backmin.domains.member.service;

import static com.backmin.domains.common.enums.ErrorInfo.DUPLICATE_EMAIL;
import static com.backmin.domains.common.enums.ErrorInfo.DUPLICATE_NICKNAME;
import static com.backmin.domains.common.enums.ErrorInfo.INCORRECT_MEMBER_SECURITY;
import static com.backmin.domains.common.enums.ErrorInfo.MEMBER_NOT_FOUND;

import com.backmin.config.exception.BusinessException;
import com.backmin.config.util.AssertThrow;
import com.backmin.domains.member.converter.MemberConverter;
import com.backmin.domains.member.domain.Member;
import com.backmin.domains.member.domain.MemberRepository;
import com.backmin.domains.member.dto.request.MemberCreateParam;
import com.backmin.domains.member.dto.request.MemberUpdateParam;
import com.backmin.domains.member.dto.response.EmailCheckResult;
import com.backmin.domains.member.dto.response.NicknameCheckResult;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberConverter memberConverter;
    private final MemberRepository memberRepository;

    @Transactional
    public void save(MemberCreateParam memberCreateParam) {
        AssertThrow.isTrue(memberRepository.existsByEmail(memberCreateParam.getEmail()), DUPLICATE_EMAIL);
        AssertThrow.isTrue(memberRepository.existsByNickName(memberCreateParam.getNickName()), DUPLICATE_NICKNAME);
        memberRepository.save(Member.of(memberCreateParam.getEmail(), memberCreateParam.getPassword(),
                memberCreateParam.getPhoneNumber(), memberCreateParam.getNickName(), memberCreateParam.getAddress()));
    }

    @Transactional
    public void update(Long memberId, MemberUpdateParam memberUpdateParam) {
        Member authenticateMember = authenticateMember(memberId, memberUpdateParam.getEmail(), memberUpdateParam.getPassword());
        memberConverter.convertUpdateDtoToMember(authenticateMember, memberUpdateParam);
    }

    public EmailCheckResult checkMemberEmail(String email) {
        return memberConverter.convertIsExistedEmailToEmailCheckResult(memberRepository.existsByEmail(email));
    }

    public NicknameCheckResult checkMemberNickname(String nickname) {
        return memberConverter.convertIsExistedNicknameToNicknameCheckResult(memberRepository.existsByNickName(nickname));
    }

    public Member authenticateMember(Long memberId, String email, String password) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND));
        AssertThrow.isFalse(StringUtils.equals(member.getEmail(), email)
                            && StringUtils.equals(member.getPassword(), password), INCORRECT_MEMBER_SECURITY);
        return member;
    }

}
