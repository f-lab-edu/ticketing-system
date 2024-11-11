package co.kr.ticketing.memberreservation.reservationtoken.domain.model;

import lombok.Builder;

@Builder
public record ReservationToken(
	String tokenKey,
	ReservationTokenValue value
) {
}
