package co.kr.ticketing.adminconcert.concert.service.dto;

import lombok.Builder;

@Builder
public record UpdateConcertDto(
	String name,
	String detailInfo,
	int runningHour,
	int runningMinute
) {
}
