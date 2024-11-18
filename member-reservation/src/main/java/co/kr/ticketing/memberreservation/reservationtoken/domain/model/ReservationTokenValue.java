package co.kr.ticketing.memberreservation.reservationtoken.domain.model;

import java.util.List;

import lombok.Builder;

@Builder
public record ReservationTokenValue(
	Long id,
	Integer sequenceNumber,
	ReservationTokenType type,
	ReservationTokenState state
) {
	public String getWaitingQName() {
		return String.join("-", List.of(type.name(), id.toString(), sequenceNumber.toString()));
	}

	public String getWaitingQCountName() {
		return String.join("-", List.of(type.name(), id.toString(), sequenceNumber.toString(), "Count"));
	}
}
