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
public class ReservationTokenReadManager {
	RedisTemplate<String, Object> redisTemplate;

	public int getTokenPositionInWaitingQ(ReservationToken reservationToken) {
		return redisTemplate.opsForZSet()
			.rank(reservationToken.value().getWaitingQName(), reservationToken.tokenKey()).intValue();
	}

	public int getNextWaitingQCount(ReservationToken token) {
		int count = redisTemplate.opsForValue().increment(token.value().getWaitingQCountName()).intValue();
		redisTemplate.expire(token.value().getWaitingQCountName(), Duration.ofMinutes(10));
		return count;
	}
}
