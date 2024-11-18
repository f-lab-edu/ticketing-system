package co.kr.ticketing.memberreservation.common.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends CustomException {
	public BadRequestException(String message) {
		super(HttpStatus.BAD_REQUEST, message);
	}
}
