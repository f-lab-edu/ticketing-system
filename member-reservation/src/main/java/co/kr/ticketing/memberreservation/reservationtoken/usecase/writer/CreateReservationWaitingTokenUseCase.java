package co.kr.ticketing.memberreservation.reservationtoken.usecase.writer;

import org.springframework.stereotype.Service;

import co.kr.ticketing.memberreservation.reservationtoken.controller.request.CreateReservationWaitingTokenRequest;
import co.kr.ticketing.memberreservation.reservationtoken.controller.response.CreateReservationWaitingTokenResponse;
import co.kr.ticketing.memberreservation.reservationtoken.domain.model.ReservationToken;
import co.kr.ticketing.memberreservation.reservationtoken.service.ReservationTokenService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CreateReservationWaitingTokenUseCase {
	ReservationTokenService reservationTokenService;

	public CreateReservationWaitingTokenResponse execute(CreateReservationWaitingTokenRequest request) {
		//todo) concert validation
		ReservationToken reservationToken = reservationTokenService.saveTokenToWaiting(request.toTokenValue());

		return CreateReservationWaitingTokenResponse.from(reservationToken);
	}
}
