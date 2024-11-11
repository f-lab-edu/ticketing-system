package co.kr.ticketing.memberreservation.reservationtoken.domain.model;

import lombok.Builder;

@Builder
public record ReservationTokenValue(
	Long id,
	Integer sequenceNumber,
	ReservationTokenType type,
	ReservationTokenState state
) {
}
