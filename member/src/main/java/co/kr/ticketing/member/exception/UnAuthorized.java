package co.kr.ticketing.member.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnAuthorized extends CustomException {
	public UnAuthorized() {
		super(HttpStatus.UNAUTHORIZED, "로그인 해주세요");
	}
}
