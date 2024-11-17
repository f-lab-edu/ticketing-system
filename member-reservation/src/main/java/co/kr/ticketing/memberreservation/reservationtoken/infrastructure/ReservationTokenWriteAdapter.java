package co.kr.ticketing.memberreservation.reservationtoken.infrastructure;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.kr.ticketing.memberreservation.reservationtoken.domain.infrastructure.ReservationTokenWriteRepository;
import co.kr.ticketing.memberreservation.reservationtoken.domain.model.ReservationToken;
import co.kr.ticketing.memberreservation.reservationtoken.domain.model.ReservationTokenValue;
import co.kr.ticketing.memberreservation.reservationtoken.infrastructure.manager.ReservationTokenReadManager;
import co.kr.ticketing.memberreservation.reservationtoken.infrastructure.manager.ReservationTokenWriteManager;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReservationTokenWriteAdapter implements ReservationTokenWriteRepository {
	ReservationTokenReadManager reservationTokenReadManager;
	ReservationTokenWriteManager reservationTokenWriteManager;

	@Override
	public void updateWaitingInfo(ReservationToken tokenValue) {
		reservationTokenWriteManager.updateWaitingQCountTTL(tokenValue);
		reservationTokenWriteManager.updateReservationTokenTTL(tokenValue);
		reservationTokenWriteManager.updateWaitingQTTL(tokenValue);
	}

	@Override
	public ReservationToken saveTokenToWaiting(ReservationTokenValue tokenValue) {

		int count = reservationTokenReadManager.getNextWaitingQCount(tokenValue);

		ReservationToken reservationToken = reservationTokenWriteManager.createReservationToken(tokenValue, count);

		reservationTokenWriteManager.insertWaitingQ(reservationToken);

		return reservationToken;
	}
}
