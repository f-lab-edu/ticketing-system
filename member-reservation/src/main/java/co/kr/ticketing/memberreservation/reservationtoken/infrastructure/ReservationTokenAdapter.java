package co.kr.ticketing.memberreservation.reservationtoken.infrastructure;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.kr.ticketing.memberreservation.reservationtoken.domain.infrastructure.ReservationTokenRepository;
import co.kr.ticketing.memberreservation.reservationtoken.domain.manager.ReservationTokenManager;
import co.kr.ticketing.memberreservation.reservationtoken.domain.model.ReservationToken;
import co.kr.ticketing.memberreservation.reservationtoken.domain.model.ReservationTokenValue;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReservationTokenAdapter implements ReservationTokenRepository {
	ReservationTokenManager reservationTokenManager;

	@Override
	public ReservationToken saveTokenToWaiting(ReservationTokenValue tokenValue) {

		int count = reservationTokenManager.getWaitingQCount(tokenValue);

		ReservationToken reservationToken = reservationTokenManager.createReservationToken(tokenValue, count);

		reservationTokenManager.insertWaitingQ(reservationToken);

		return reservationToken;
	}
}
