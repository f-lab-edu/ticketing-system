package co.kr.ticketing.memberreservation.reservationtoken.domain.infrastructure;

import co.kr.ticketing.memberreservation.reservationtoken.domain.model.ReservationToken;

public interface ReservationTokenReadRepository {
	ReservationToken getReservationTokenByTokenKey(String key);

	int getTokenPositionInWaitingQ(ReservationToken tokenValue);
}
