package co.kr.ticketing.adminconcert.concert.domain.model;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record Round(
	Long id,
	LocalDateTime startDateTime,
	Integer sequenceNumber
) {
}
