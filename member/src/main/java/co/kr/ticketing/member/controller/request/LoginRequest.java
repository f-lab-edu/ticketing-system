package co.kr.ticketing.member.controller.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
	@NotBlank
	String phoneNumber,
	@NotBlank
	String password
) {
}
