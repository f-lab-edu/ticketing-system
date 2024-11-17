package co.kr.ticketing.memberreservation.reservationtoken.infrastructure.manager;

import java.time.Duration;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import co.kr.ticketing.memberreservation.reservationtoken.domain.infrastructure.ReservationTokenGenerator;
import co.kr.ticketing.memberreservation.reservationtoken.domain.model.ReservationToken;
import co.kr.ticketing.memberreservation.reservationtoken.domain.model.ReservationTokenValue;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReservationTokenWriteManager {
	ReservationTokenGenerator tokenGenerator;
	RedisTemplate<String, Object> redisTemplate;

	public ReservationToken createReservationToken(ReservationTokenValue tokenValue, int ordering) {
		ReservationTokenValue tokenValueWithCount = tokenValue.setOrdering(ordering);
		return tokenGenerator.generate(tokenValueWithCount);
	}

	public void insertWaitingQ(ReservationToken reservationToken) {
		redisTemplate.opsForZSet()
			.incrementScore(reservationToken.value().getWaitingQName(), reservationToken.tokenKey(),
				reservationToken.value().ordering());

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
