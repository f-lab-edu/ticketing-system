package co.kr.ticketing.adminconcert.common.dto;

import lombok.Builder;

@Builder
public record UpdatedDto(
	Long id
) {
	public static UpdatedDto from(Long id) {
		return UpdatedDto.builder()
			.id(id)
			.build();
	}
}
