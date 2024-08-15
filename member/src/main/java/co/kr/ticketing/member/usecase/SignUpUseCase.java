package co.kr.ticketing.member.usecase;

import org.springframework.stereotype.Service;

import co.kr.ticketing.member.controller.request.SignUpRequest;
import co.kr.ticketing.member.domain.service.MemberService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SignUpUseCase {
	MemberService memberService;

	public void execute(SignUpRequest request) {
		memberService.create(request.toDto());
	}
}
