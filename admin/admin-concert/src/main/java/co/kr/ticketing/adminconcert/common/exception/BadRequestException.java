package co.kr.ticketing.adminconcert.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@Getter
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends CustomException {
	private final String code;

	public BadRequestException(String message) {
		super(HttpStatus.BAD_REQUEST, message);
		this.code = HttpStatus.BAD_REQUEST.name();
	}

	public BadRequestException(String code, String message) {
		super(HttpStatus.BAD_REQUEST, message);
		this.code = code;
	}
}
