package co.kr.ticketing.adminconcert.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DuplicateException extends CustomException {
	public DuplicateException(String message) {
		super(HttpStatus.CONFLICT, message);
	}
}
