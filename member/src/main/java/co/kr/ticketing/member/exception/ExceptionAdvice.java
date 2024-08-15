package co.kr.ticketing.member.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ExceptionResponse> badRequestException(CustomException exception) {
		ExceptionResponse response = ExceptionResponse.from(exception);
		log.warn("CustomRuntimeException 발생 : code: {}, message: {}", response.code(), response.message());
		return new ResponseEntity<>(response, exception.getStatus());
	}
}