package co.kr.ticketing.member.usecase;

import org.springframework.stereotype.Service;

import co.kr.ticketing.member.controller.request.LoginRequest;
import co.kr.ticketing.member.domain.model.Member;
import co.kr.ticketing.member.domain.service.MemberService;
import co.kr.ticketing.member.exception.BadRequestException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LoginUseCase {
	MemberService memberService;

	public Member execute(LoginRequest request) {
		Member member = memberService.findByPhoneNumber(request.phoneNumber())
			.orElseThrow(() -> new BadRequestException("로그인 정보가 일치하지 않습니다"));

		if (!memberService.isMatchPassword(member, request.password())) {
			throw new BadRequestException("로그인 정보가 일치하지 않습니다");
		}

		return member;
	}
}
