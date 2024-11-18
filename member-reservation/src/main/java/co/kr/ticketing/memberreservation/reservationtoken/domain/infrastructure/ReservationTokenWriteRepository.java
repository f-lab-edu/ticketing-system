package co.kr.ticketing.memberreservation.reservationtoken.domain.infrastructure;

import co.kr.ticketing.memberreservation.reservationtoken.domain.model.ReservationToken;

public interface ReservationTokenWriteRepository {
	void updateWaitingInfo(ReservationToken tokenValue);

	void saveTokenToWaitingQ(ReservationToken token);
}
