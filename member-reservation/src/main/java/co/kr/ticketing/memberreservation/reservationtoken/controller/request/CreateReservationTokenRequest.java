package co.kr.ticketing.memberreservation.reservationtoken.controller.request;

import co.kr.ticketing.memberreservation.reservationtoken.domain.manager.dto.CreateReservationTokenVo;
import co.kr.ticketing.memberreservation.reservationtoken.domain.model.ReservationTokenType;
import jakarta.validation.constraints.NotNull;

public record CreateReservationTokenRequest(
	@NotNull
	Long concertId,
	@NotNull
	Integer sequenceNumber
) {
	public CreateReservationTokenVo toDto() {
		return CreateReservationTokenVo.builder()
			.id(concertId)
			.sequenceNumber(sequenceNumber)
			.type(ReservationTokenType.CONCERT)
			.build();
	}
}
