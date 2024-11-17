package co.kr.ticketing.memberreservation.reservationtoken.infrastructure;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.kr.ticketing.memberreservation.reservationtoken.domain.infrastructure.ReservationTokenReadRepository;
import co.kr.ticketing.memberreservation.reservationtoken.domain.model.ReservationToken;
import co.kr.ticketing.memberreservation.reservationtoken.infrastructure.manager.ReservationTokenReadManager;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReservationTokenReadAdapter implements ReservationTokenReadRepository {
	ReservationTokenReadManager reservationTokenReadManager;

	@Override
	public ReservationToken getReservationTokenByTokenKey(String key) {
		return reservationTokenReadManager.getReservationTokenByTokenKey(key);
	}

	@Override
	public int getTokenPositionInWaitingQ(ReservationToken reservationToken) {
		return reservationTokenReadManager.getTokenPositionInWaitingQ(reservationToken);
	}
}
