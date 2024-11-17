package co.kr.ticketing.memberreservation.reservationtoken.controller.response;

import lombok.Builder;

@Builder
public record GetCurrentReservationTokenPositionResponse(
	int position
) {
	public static GetCurrentReservationTokenPositionResponse from(int position) {
		return GetCurrentReservationTokenPositionResponse.builder()
			.position(position)
			.build();
	}
}
