package co.kr.ticketing.memberreservation.reservationtoken.infrastructure;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.kr.ticketing.memberreservation.reservationtoken.domain.infrastructure.ReservationTokenRepository;
import co.kr.ticketing.memberreservation.reservationtoken.domain.manager.ReservationTokenManager;
import co.kr.ticketing.memberreservation.reservationtoken.domain.manager.dto.CreateReservationTokenVo;
import co.kr.ticketing.memberreservation.reservationtoken.domain.model.ReservationToken;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReservationTokenAdapter implements ReservationTokenRepository {
	RedisTemplate<String, Object> redisTemplate;
	ReservationTokenManager reservationTokenManager;

	@Override
	@Transactional
	public ReservationToken save(CreateReservationTokenVo createReservationTokenVo) {
		ReservationToken reservationToken = reservationTokenManager.createReservationToken(createReservationTokenVo);

		var redisOperation = redisTemplate.opsForValue();
		redisOperation.set(reservationToken.tokenKey(), reservationToken.value());

		return reservationToken;
	}
}
