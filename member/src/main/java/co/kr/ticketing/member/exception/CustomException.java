package co.kr.ticketing.member.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
	private final HttpStatus status;

	public CustomException(final HttpStatus status, final String message) {
		super(message);
		this.status = status;
	}
}
