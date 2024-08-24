package co.kr.ticketing.member.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ConflictException extends CustomException {
	public ConflictException(String message) {
		super(HttpStatus.CONFLICT, message);
	}
}
