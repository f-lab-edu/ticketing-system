package co.kr.ticketing.member.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@Getter
@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
public class ServerException extends CustomException {
	private final Throwable cause;

	public ServerException(Throwable cause) {
		super(HttpStatus.SERVICE_UNAVAILABLE, "잠시 후 다시 이용해주세요");
		this.cause = cause;
	}
}
