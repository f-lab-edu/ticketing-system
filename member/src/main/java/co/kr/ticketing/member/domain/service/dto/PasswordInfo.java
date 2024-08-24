package co.kr.ticketing.member.domain.service.dto;

import lombok.Builder;

@Builder
public record PasswordInfo(
	String encodedPassword,
	String salt
) {
}
