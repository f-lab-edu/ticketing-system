package co.kr.ticketing.memberreservation.reservationtoken.domain.manager;

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
public class ReservationTokenManager {
	ReservationTokenGenerator tokenGenerator;
	RedisTemplate<String, Object> redisTemplate;

	public int getWaitingQCount(ReservationTokenValue tokenValue) {
		int count = redisTemplate.opsForValue().increment(tokenValue.getWaitingQCountName()).intValue();
		redisTemplate.expire(tokenValue.getWaitingQCountName(), Duration.ofMinutes(10));
		return count;
	}

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
}
