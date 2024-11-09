package co.kr.ticketing.memberconcert.concert.domain.model;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record Round(
	Long id,
	LocalDateTime startDateTime,
	Integer sequenceNumber
) {
}
