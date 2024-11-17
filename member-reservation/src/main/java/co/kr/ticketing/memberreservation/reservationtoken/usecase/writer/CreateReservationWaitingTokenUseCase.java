package co.kr.ticketing.memberreservation.reservationtoken.usecase.writer;

import org.springframework.stereotype.Service;

import co.kr.ticketing.memberreservation.reservationtoken.controller.request.CreateReservationWaitingTokenRequest;
import co.kr.ticketing.memberreservation.reservationtoken.controller.response.CreateReservationWaitingTokenResponse;
import co.kr.ticketing.memberreservation.reservationtoken.domain.model.ReservationToken;
import co.kr.ticketing.memberreservation.reservationtoken.service.ReservationWaitingTokenService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CreateReservationWaitingTokenUseCase {
	ReservationWaitingTokenService reservationWaitingTokenService;

	public CreateReservationWaitingTokenResponse execute(CreateReservationWaitingTokenRequest request) {
		//todo) concert validation
		ReservationToken reservationToken = reservationWaitingTokenService.createWaitingToken(request.toTokenValue());

		return CreateReservationWaitingTokenResponse.from(reservationToken);
	}
}
