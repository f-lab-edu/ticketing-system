package co.kr.ticketing.memberreservation.reservationtoken.domain.infrastructure;

import co.kr.ticketing.memberreservation.reservationtoken.domain.model.ReservationToken;

public interface ReservationTokenReadRepository {

	int getTokenPositionInWaitingQ(ReservationToken tokenValue);
}
