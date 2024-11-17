package co.kr.ticketing.memberreservation.reservationtoken.domain.infrastructure;

import co.kr.ticketing.memberreservation.reservationtoken.domain.model.ReservationToken;
import co.kr.ticketing.memberreservation.reservationtoken.domain.model.ReservationTokenValue;

public interface ReservationTokenGenerator {
	ReservationToken parsing(String tokenKey);

	ReservationToken generate(ReservationTokenValue tokenValue);
}
