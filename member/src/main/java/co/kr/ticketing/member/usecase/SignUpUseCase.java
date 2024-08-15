package co.kr.ticketing.member.usecase;

import org.springframework.stereotype.Service;

import co.kr.ticketing.member.controller.request.SignUpRequest;
import co.kr.ticketing.member.domain.service.MemberService;
import co.kr.ticketing.member.exception.ConflicException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SignUpUseCase {
	MemberService memberService;

	public void execute(SignUpRequest request) {
		if (memberService.isExistMember(request.phoneNumber())) {
			throw new ConflicException("이미 가입된 회원입니다");
		}

		memberService.create(request.toDto());
	}
}
