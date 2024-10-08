package co.kr.ticketing.adminconcert.concert.controller.request;

import co.kr.ticketing.adminconcert.concert.service.dto.UpdateConcertDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateConcertRequest(
	@NotBlank
	String name,
	String detailInfo,
	@NotNull
	int runningHour,
	@NotNull
	int runningMinute
) {
	public UpdateConcertDto toDto() {
		return UpdateConcertDto.builder()
			.name(name)
			.detailInfo(detailInfo)
			.runningHour(runningHour)
			.runningMinute(runningMinute)
			.build();
	}
}
