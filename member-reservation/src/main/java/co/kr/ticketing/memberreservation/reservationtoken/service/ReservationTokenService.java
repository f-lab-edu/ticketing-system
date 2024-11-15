package co.kr.ticketing.memberreservation.reservationtoken.service;

import org.springframework.stereotype.Service;

import co.kr.ticketing.memberreservation.reservationtoken.domain.infrastructure.ReservationTokenRepository;
import co.kr.ticketing.memberreservation.reservationtoken.domain.model.ReservationToken;
import co.kr.ticketing.memberreservation.reservationtoken.domain.model.ReservationTokenValue;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReservationTokenService {
	ReservationTokenRepository reservationTokenRepository;

	public ReservationToken saveTokenToWaiting(ReservationTokenValue tokenValue) {
		return reservationTokenRepository.saveTokenToWaiting(tokenValue);
	}
}
