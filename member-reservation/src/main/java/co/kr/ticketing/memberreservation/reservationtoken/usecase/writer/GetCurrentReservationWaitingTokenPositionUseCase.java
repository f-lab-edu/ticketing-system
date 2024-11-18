package co.kr.ticketing.memberreservation.reservationtoken.usecase.writer;

import org.springframework.stereotype.Service;

import co.kr.ticketing.memberreservation.reservationtoken.controller.response.GetCurrentReservationWaitingTokenPositionResponse;
import co.kr.ticketing.memberreservation.reservationtoken.domain.model.ReservationToken;
import co.kr.ticketing.memberreservation.reservationtoken.service.ReservationWaitingTokenService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetCurrentReservationWaitingTokenPositionUseCase {
	ReservationWaitingTokenService reservationWaitingTokenService;

	public GetCurrentReservationWaitingTokenPositionResponse execute(String token) {
		ReservationToken reservationToken = reservationWaitingTokenService.getReservationTokenByTokenKey(token);

		int position = reservationWaitingTokenService.getTokenPositionInWaitingQ(reservationToken);

		reservationWaitingTokenService.updateWaitingInfo(reservationToken);

		return GetCurrentReservationWaitingTokenPositionResponse.from(position + 1);
	}

}
