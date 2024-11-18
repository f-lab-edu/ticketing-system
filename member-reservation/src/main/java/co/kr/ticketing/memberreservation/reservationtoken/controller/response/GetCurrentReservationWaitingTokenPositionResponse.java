package co.kr.ticketing.memberreservation.reservationtoken.controller.response;

import lombok.Builder;

@Builder
public record GetCurrentReservationWaitingTokenPositionResponse(
	int position
) {
	public static GetCurrentReservationWaitingTokenPositionResponse from(int position) {
		return GetCurrentReservationWaitingTokenPositionResponse.builder()
			.position(position)
			.build();
	}
}
