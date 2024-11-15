package co.kr.ticketing.memberconcert.common.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends CustomException {
	public BadRequestException(String message) {
		super(HttpStatus.BAD_REQUEST, message);
	}
}
