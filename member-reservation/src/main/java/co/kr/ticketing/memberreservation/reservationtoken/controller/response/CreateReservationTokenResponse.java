package co.kr.ticketing.memberreservation.reservationtoken.controller.response;

import co.kr.ticketing.memberreservation.reservationtoken.domain.model.ReservationToken;
import lombok.Builder;

@Builder
public record CreateReservationTokenResponse(
	String token
) {
	public static CreateReservationTokenResponse from(ReservationToken reservationToken) {
		return CreateReservationTokenResponse.builder()
			.token(reservationToken.tokenKey())
			.build();
	}
}
