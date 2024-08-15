package co.kr.ticketing.member.controller.request;

import co.kr.ticketing.member.domain.service.dto.CreateMemberDto;
import jakarta.validation.constraints.NotBlank;

public record SignUpRequest(
	@NotBlank
	String phoneNumber,
	@NotBlank
	String password
) {
	public CreateMemberDto toDto() {
		return CreateMemberDto.builder()
			.phoneNumber(phoneNumber)
			.password(password)
			.build();
	}
}
