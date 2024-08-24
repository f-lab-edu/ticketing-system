package co.kr.ticketing.member.domain.service.dto;

import co.kr.ticketing.member.domain.model.Member;
import lombok.Builder;

@Builder
public record CreateMemberDto(
	String phoneNumber,
	String password
) {
	public Member toModel(PasswordInfo passwordInfo) {
		return Member.builder()
			.phoneNumber(phoneNumber)
			.password(passwordInfo.encodedPassword())
			.salt(passwordInfo.salt())
			.build();
	}
}
