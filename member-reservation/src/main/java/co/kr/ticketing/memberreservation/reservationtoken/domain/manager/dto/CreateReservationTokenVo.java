package co.kr.ticketing.memberreservation.reservationtoken.domain.manager.dto;

import co.kr.ticketing.memberreservation.reservationtoken.domain.model.ReservationTokenState;
import co.kr.ticketing.memberreservation.reservationtoken.domain.model.ReservationTokenType;
import co.kr.ticketing.memberreservation.reservationtoken.domain.model.ReservationTokenValue;
import lombok.Builder;

@Builder
public record CreateReservationTokenVo(
	Long id,
	Integer sequenceNumber,
	ReservationTokenType type
) {
	public ReservationTokenValue toTokenValue() {
		return ReservationTokenValue.builder()
			.id(id)
			.sequenceNumber(sequenceNumber)
			.type(type)
			.state(ReservationTokenState.WAITING)
			.build();
	}
}
