package co.kr.ticketing.memberreservation.reservationtoken.domain.infrastructure;

import co.kr.ticketing.memberreservation.reservationtoken.domain.manager.dto.CreateReservationTokenVo;
import co.kr.ticketing.memberreservation.reservationtoken.domain.model.ReservationToken;

public interface ReservationTokenRepository {
	ReservationToken save(CreateReservationTokenVo createReservationTokenVo);
}
