package co.kr.ticketing.member.exception;

import lombok.Builder;

@Builder
public record ExceptionResponse(
	int code,
	String message
) {
	public static ExceptionResponse from(CustomException exception){
		return ExceptionResponse.builder()
			.code(exception.getStatus().value())
			.message(exception.getMessage())
			.build();
	}
}
