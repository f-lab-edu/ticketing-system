package co.kr.ticketing.adminconcert.common.dto;

import lombok.Builder;

@Builder
public record CreatedDto(
	Long id
) {
	public static CreatedDto from(Long id) {
		return CreatedDto.builder()
			.id(id)
			.build();
	}
}
