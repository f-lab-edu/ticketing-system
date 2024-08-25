package co.kr.ticketing.member.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import co.kr.ticketing.member.common.ResponseDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {
	@ExceptionHandler(ServerException.class)
	public ResponseEntity<ResponseDto<String>> serverException(ServerException exception) {
		ResponseDto<String> responseDto = new ResponseDto<>(exception.getStatus().toString(),
			exception.getMessage());
		log.error("Server Error 발생 : origin exception: {}", exception.getCause().getClass().getName());
		exception.getCause().printStackTrace();

		return new ResponseEntity<>(responseDto, exception.getStatus());
	}

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ResponseDto<String>> customException(CustomException exception) {
		ResponseDto<String> responseDto = new ResponseDto<>(exception.getStatus().toString(),
			exception.getMessage());
		log.warn("CustomRuntimeException 발생 : code: {}, message: {}", responseDto.getCode(), responseDto.getBody());
		return new ResponseEntity<>(responseDto, exception.getStatus());
	}
}