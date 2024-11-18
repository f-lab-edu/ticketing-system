package co.kr.ticketing.memberreservation.reservationtoken.controller.response;

import co.kr.ticketing.memberreservation.reservationtoken.domain.model.ReservationToken;
import lombok.Builder;

@Builder
public record CreateReservationWaitingTokenResponse(
	String token
) {
	public static CreateReservationWaitingTokenResponse from(ReservationToken reservationToken) {
		return CreateReservationWaitingTokenResponse.builder()
			.token(reservationToken.tokenKey())
			.build();
	}
}
