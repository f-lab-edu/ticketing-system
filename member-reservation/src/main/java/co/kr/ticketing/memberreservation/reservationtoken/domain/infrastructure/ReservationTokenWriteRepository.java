package co.kr.ticketing.memberreservation.reservationtoken.domain.infrastructure;

import co.kr.ticketing.memberreservation.reservationtoken.domain.model.ReservationToken;
import co.kr.ticketing.memberreservation.reservationtoken.domain.model.ReservationTokenValue;

public interface ReservationTokenWriteRepository {
	void updateWaitingInfo(ReservationToken tokenValue);
	
	ReservationToken saveTokenToWaiting(ReservationTokenValue tokenValue);
}
