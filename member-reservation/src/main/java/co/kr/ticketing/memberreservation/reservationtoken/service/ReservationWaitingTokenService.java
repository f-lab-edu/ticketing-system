package co.kr.ticketing.memberreservation.reservationtoken.service;

import org.springframework.stereotype.Service;

import co.kr.ticketing.memberreservation.reservationtoken.domain.infrastructure.ReservationTokenGenerator;
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
public class ReservationWaitingTokenService {
	ReservationTokenGenerator tokenGenerator;
	ReservationTokenWriteRepository reservationTokenWriteRepository;
	ReservationTokenReadRepository reservationTokenReadRepository;

	public ReservationToken getReservationTokenByTokenKey(String key) {
		return tokenGenerator.parsing(key);
	}

	//todo) calc position
	public int getTokenPositionInWaitingQ(ReservationToken tokenValue) {
		return reservationTokenReadRepository.getTokenPositionInWaitingQ(tokenValue);
	}

	public void updateWaitingInfo(ReservationToken tokenValue) {
		reservationTokenWriteRepository.updateWaitingInfo(tokenValue);
	}

	public ReservationToken createWaitingToken(ReservationTokenValue tokenValue) {
		ReservationToken waitingToken = tokenGenerator.generate(tokenValue);

		reservationTokenWriteRepository.saveTokenToWaitingQ(waitingToken);

		return waitingToken;
	}

	public void deleteWaitingToken(ReservationToken token) {
		//delete token and remove from waiting q
	}

	public ReservationToken createReservationToken(ReservationTokenValue tokenValue) {
		//create reservation token
		//insert reservation token to processing q
		return null;
	}
}
