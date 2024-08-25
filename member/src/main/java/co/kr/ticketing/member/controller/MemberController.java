package co.kr.ticketing.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.kr.ticketing.member.auth.aop.LoginCheck;
import co.kr.ticketing.member.auth.config.AuthConfig;
import co.kr.ticketing.member.auth.util.TokenUtil;
import co.kr.ticketing.member.common.ResponseDto;
import co.kr.ticketing.member.controller.request.LoginRequest;
import co.kr.ticketing.member.controller.request.SignUpRequest;
import co.kr.ticketing.member.controller.response.MemberResponseCode;
import co.kr.ticketing.member.domain.model.Member;
import co.kr.ticketing.member.usecase.LoginUseCase;
import co.kr.ticketing.member.usecase.SignUpUseCase;
import co.kr.ticketing.member.util.CookieUtil;
import co.kr.ticketing.member.util.dto.CookieDto;
import jakarta.servlet.http.HttpServletResponse;
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
	CookieUtil cookieUtil;
	TokenUtil tokenUtil;
	SignUpUseCase signupUseCase;
	LoginUseCase loginUseCase;

	@PostMapping
	public ResponseEntity<ResponseDto> SignUp(@RequestBody @Valid SignUpRequest request) {
		signupUseCase.execute(request);

		return ResponseEntity.status(HttpStatus.CREATED)
			.body(new ResponseDto<>(MemberResponseCode.MEMBER_CREATED.name()));
	}

	@PostMapping("/login")
	public ResponseEntity<ResponseDto> Login(@RequestBody @Valid LoginRequest request, HttpServletResponse response) {
		Member member = loginUseCase.execute(request);

		ResponseCookie responseCookie = createLoginTokenCookie(member);

		response.addHeader("Set-Cookie", responseCookie.toString());

		return ResponseEntity.ok(new ResponseDto<>(MemberResponseCode.LOGIN_SUCCESS.name()));
	}

	@LoginCheck
	@PostMapping("/logout")
	public ResponseEntity<ResponseDto> Logout(HttpServletResponse response) {
		ResponseCookie responseCookie = invalidLoginTokenCookie();

		response.addHeader("Set-Cookie", responseCookie.toString());

		return ResponseEntity.ok(new ResponseDto<>(MemberResponseCode.LOGOUT_SUCCESS.name()));
	}

	private ResponseCookie createLoginTokenCookie(Member member) {
		String loginToken = tokenUtil.generateToken(member);

		return cookieUtil.createTokenCookie(CookieDto.builder()
			.name(AuthConfig.LOGIN_COOKIE_NAME)
			.value(loginToken)
			.isEncode(true)
			.httpOnly(true)
			.maxAge(AuthConfig.TOKEN_VALID_TIME)
			.build()
		);
	}

	private ResponseCookie invalidLoginTokenCookie() {
		return cookieUtil.createTokenCookie(CookieDto.builder()
			.name(AuthConfig.LOGIN_COOKIE_NAME)
			.value(null)
			.isEncode(false)
			.httpOnly(true)
			.maxAge(0)
			.build()
		);
	}
}
