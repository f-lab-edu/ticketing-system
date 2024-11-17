package co.kr.ticketing.memberreservation.reservationtoken.infrastructure.manager;

import java.time.Duration;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import co.kr.ticketing.memberreservation.reservationtoken.domain.model.ReservationToken;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReservationTokenWriteManager {
	RedisTemplate<String, Object> redisTemplate;

	public void insertWaitingQ(ReservationToken reservationToken, int ordering) {
		redisTemplate.opsForZSet()
			.incrementScore(reservationToken.value().getWaitingQName(), reservationToken.tokenKey(), ordering);

		redisTemplate.opsForValue().set(reservationToken.tokenKey(), reservationToken.value());
	}

	public void updateWaitingQCountTTL(ReservationToken reservationToken) {
		redisTemplate.expire(reservationToken.value().getWaitingQCountName(), Duration.ofMinutes(10));
	}

	public void updateReservationTokenTTL(ReservationToken tokenValue) {
		redisTemplate.expire(tokenValue.tokenKey(), Duration.ofMinutes(1));
	}

	public void updateWaitingQTTL(ReservationToken tokenValue) {
		redisTemplate.expire(tokenValue.value().getWaitingQName(), Duration.ofMinutes(5));
	}
}
