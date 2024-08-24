package co.kr.ticketing.member.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends CustomException {
	public BadRequestException(String message) {
		super(HttpStatus.BAD_REQUEST, message);
	}
}
