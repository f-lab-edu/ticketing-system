package co.kr.ticketing.memberreservation.reservationtoken.controller.request;

import co.kr.ticketing.memberreservation.reservationtoken.domain.model.ReservationTokenState;
import co.kr.ticketing.memberreservation.reservationtoken.domain.model.ReservationTokenType;
import co.kr.ticketing.memberreservation.reservationtoken.domain.model.ReservationTokenValue;
import jakarta.validation.constraints.NotNull;

public record CreateReservationWaitingTokenRequest(
	@NotNull
	Long concertId,
	@NotNull
	Integer sequenceNumber
) {
	public ReservationTokenValue toTokenValue() {
		return ReservationTokenValue.builder()
			.id(concertId)
			.sequenceNumber(sequenceNumber)
			.type(ReservationTokenType.CONCERT)
			.state(ReservationTokenState.WAITING)
			.build();
	}
}
