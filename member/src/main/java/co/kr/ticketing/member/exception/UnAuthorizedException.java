package co.kr.ticketing.member.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnAuthorizedException extends CustomException {
	public UnAuthorizedException() {
		super(HttpStatus.UNAUTHORIZED, "로그인 해주세요");
	}
}
