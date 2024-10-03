package co.kr.ticketing.adminconcert.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
		log.error("CustomRuntimeException 발생 : code: {}, message: {}", responseDto.getCode(), responseDto.getBody());
		return new ResponseEntity<>(responseDto, exception.getStatus());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseDto<String>> methodArgumentNotValidException(
		MethodArgumentNotValidException exception) {
		HttpStatus badRequest = HttpStatus.BAD_REQUEST;
		ResponseDto<String> responseDto = new ResponseDto<>(badRequest.toString(), "요청 값이 잘 못 됐습니다");
		log.error("MethodArgumentNotValidException 발생 : message: {}", exception.getMessage());
		return new ResponseEntity<>(responseDto, badRequest);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ResponseDto<String>> badRequestException(BadRequestException exception) {
		ResponseDto<String> responseDto = new ResponseDto<>(exception.getCode(), exception.getMessage());
		log.error("BadRequestException 발생 : message: {}", exception.getMessage());
		return new ResponseEntity<>(responseDto, exception.getStatus());
	}
}