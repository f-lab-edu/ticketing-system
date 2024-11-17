package co.kr.ticketing.memberreservation.reservationtoken.usecase.writer;

import org.springframework.stereotype.Service;

import co.kr.ticketing.memberreservation.reservationtoken.controller.response.GetCurrentReservationWaitingTokenPositionResponse;
import co.kr.ticketing.memberreservation.reservationtoken.domain.model.ReservationToken;
import co.kr.ticketing.memberreservation.reservationtoken.service.ReservationTokenService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetCurrentReservationWaitingTokenPositionUseCase {
	ReservationTokenService reservationTokenService;

	public GetCurrentReservationWaitingTokenPositionResponse execute(String token) {
		ReservationToken reservationToken = reservationTokenService.getReservationTokenByTokenKey(token);

		//todo) calc position
		int position = reservationTokenService.getTokenPositionInWaitingQ(reservationToken);

		reservationTokenService.updateWaitingInfo(reservationToken);

		return GetCurrentReservationWaitingTokenPositionResponse.from(position + 1);
	}

}
