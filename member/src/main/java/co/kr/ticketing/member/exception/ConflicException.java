package co.kr.ticketing.member.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ConflicException extends CustomException {
	public ConflicException(String message) {
		super(HttpStatus.CONFLICT, message);
	}
}
