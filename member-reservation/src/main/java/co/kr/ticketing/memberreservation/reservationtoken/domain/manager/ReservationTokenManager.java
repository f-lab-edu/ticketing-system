package co.kr.ticketing.memberreservation.reservationtoken.domain.manager;

import org.springframework.stereotype.Component;

import co.kr.ticketing.memberreservation.reservationtoken.domain.infrastructure.ReservationTokenGenerator;
import co.kr.ticketing.memberreservation.reservationtoken.domain.manager.dto.CreateReservationTokenVo;
import co.kr.ticketing.memberreservation.reservationtoken.domain.model.ReservationToken;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReservationTokenManager {
	ReservationTokenGenerator tokenGenerator;

	public ReservationToken createReservationToken(CreateReservationTokenVo createReservationTokenVo) {
		return tokenGenerator.generate(createReservationTokenVo.toTokenValue());
	}
}
