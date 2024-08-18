package co.kr.ticketing.member.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequest extends CustomException {
	public BadRequest(String message) {
		super(HttpStatus.BAD_REQUEST, message);
	}
}
