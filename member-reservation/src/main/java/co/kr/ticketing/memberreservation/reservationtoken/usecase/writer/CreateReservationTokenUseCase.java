package co.kr.ticketing.memberreservation.reservationtoken.usecase.writer;

import org.springframework.stereotype.Service;

import co.kr.ticketing.memberreservation.reservationtoken.controller.request.CreateReservationTokenRequest;
import co.kr.ticketing.memberreservation.reservationtoken.controller.response.CreateReservationTokenResponse;
import co.kr.ticketing.memberreservation.reservationtoken.domain.model.ReservationToken;
import co.kr.ticketing.memberreservation.reservationtoken.service.ReservationTokenService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CreateReservationTokenUseCase {
	ReservationTokenService reservationTokenService;

	public CreateReservationTokenResponse execute(CreateReservationTokenRequest request) {
		//todo) concert validation
		ReservationToken reservationToken = reservationTokenService.createReservationToken(request.toDto());

		return CreateReservationTokenResponse.from(reservationToken);
	}
}
