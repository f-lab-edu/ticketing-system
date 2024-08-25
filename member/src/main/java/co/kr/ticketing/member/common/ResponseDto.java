package co.kr.ticketing.member.common;

import lombok.Getter;

@Getter
public class ResponseDto<T> {
	private final String code;
	private final T body;

	public ResponseDto(String code) {
		this.code = code;
		this.body = null;
	}

	public ResponseDto(String code, T body) {
		this.code = code;
		this.body = body;
	}
}
