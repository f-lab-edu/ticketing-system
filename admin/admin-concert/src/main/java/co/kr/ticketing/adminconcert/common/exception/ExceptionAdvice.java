package co.kr.ticketing.adminconcert.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import co.kr.ticketing.adminconcert.common.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ResponseDto<String>> customException(CustomException exception) {
		ResponseDto<String> responseDto = new ResponseDto<>(exception.getStatus().toString(),
			exception.getMessage());
		log.warn("CustomRuntimeException 발생 : code: {}, message: {}", responseDto.getCode(), responseDto.getBody());
		return new ResponseEntity<>(responseDto, exception.getStatus());
	}
}