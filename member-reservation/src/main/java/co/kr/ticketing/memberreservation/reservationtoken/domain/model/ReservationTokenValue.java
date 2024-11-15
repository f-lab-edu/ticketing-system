package co.kr.ticketing.memberreservation.reservationtoken.domain.model;

import java.util.List;

import lombok.Builder;

@Builder
public record ReservationTokenValue(
	Long id,
	Integer sequenceNumber,
	ReservationTokenType type,
	ReservationTokenState state,
	int ordering
) {
	public ReservationTokenValue setOrdering(int ordering) {
		return ReservationTokenValue.builder()
			.id(this.id)
			.sequenceNumber(this.sequenceNumber)
			.type(this.type)
			.state(this.state)
			.ordering(ordering)
			.build();
	}

	public String getWaitingQName() {
		return String.join("-", List.of(type.name(), id.toString(), sequenceNumber.toString()));
	}

	public String getWaitingQCountName() {
		return String.join("-", List.of(type.name(), id.toString(), sequenceNumber.toString(), "Count"));
	}
}
