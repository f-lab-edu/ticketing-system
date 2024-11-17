package co.kr.ticketing.memberreservation.reservationtoken.service;

import org.springframework.stereotype.Service;

import co.kr.ticketing.memberreservation.reservationtoken.domain.infrastructure.ReservationTokenReadRepository;
import co.kr.ticketing.memberreservation.reservationtoken.domain.infrastructure.ReservationTokenWriteRepository;
import co.kr.ticketing.memberreservation.reservationtoken.domain.model.ReservationToken;
import co.kr.ticketing.memberreservation.reservationtoken.domain.model.ReservationTokenValue;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReservationTokenService {
	ReservationTokenWriteRepository reservationTokenWriteRepository;
	ReservationTokenReadRepository reservationTokenReadRepository;

	public ReservationToken getReservationTokenByTokenKey(String key) {
		return reservationTokenReadRepository.getReservationTokenByTokenKey(key);
	}

	public int getTokenPositionInWaitingQ(ReservationToken tokenValue) {
		return reservationTokenReadRepository.getTokenPositionInWaitingQ(tokenValue);
	}

	public void updateWaitingInfo(ReservationToken tokenValue) {
		reservationTokenWriteRepository.updateWaitingInfo(tokenValue);
	}

	public ReservationToken saveTokenToWaiting(ReservationTokenValue tokenValue) {
		return reservationTokenWriteRepository.saveTokenToWaiting(tokenValue);
	}
}
