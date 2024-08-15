package co.kr.ticketing.member.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.kr.ticketing.member.controller.request.SignUpRequest;
import co.kr.ticketing.member.usecase.SignUpUseCase;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Validated
@RestController
@RequestMapping(value = "/members")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MemberController {
	SignUpUseCase signupUseCase;

	@PostMapping
	public void SignUp(@RequestBody @Valid SignUpRequest request) {
		signupUseCase.execute(request);
	}
}
