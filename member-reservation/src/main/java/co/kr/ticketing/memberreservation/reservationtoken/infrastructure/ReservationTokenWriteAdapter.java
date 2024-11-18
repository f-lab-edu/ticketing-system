package co.kr.ticketing.memberreservation.reservationtoken.infrastructure;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.kr.ticketing.memberreservation.reservationtoken.domain.infrastructure.ReservationTokenWriteRepository;
import co.kr.ticketing.memberreservation.reservationtoken.domain.model.ReservationToken;
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
	public void saveTokenToWaitingQ(ReservationToken token) {
		int count = reservationTokenReadManager.getNextWaitingQCount(token);

		reservationTokenWriteManager.insertWaitingQ(token, count);
	}
}
