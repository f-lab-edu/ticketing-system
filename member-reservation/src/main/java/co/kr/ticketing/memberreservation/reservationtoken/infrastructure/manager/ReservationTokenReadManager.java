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
public class ReservationTokenReadManager {
	ReservationTokenGenerator tokenGenerator;
	RedisTemplate<String, Object> redisTemplate;

	public ReservationToken getReservationTokenByTokenKey(String key) {
		return tokenGenerator.parsing(key);
	}

	public int getTokenPositionInWaitingQ(ReservationToken reservationToken) {
		return redisTemplate.opsForZSet()
			.rank(reservationToken.value().getWaitingQName(), reservationToken.tokenKey()).intValue();
	}

	public int getNextWaitingQCount(ReservationTokenValue tokenValue) {
		int count = redisTemplate.opsForValue().increment(tokenValue.getWaitingQCountName()).intValue();
		redisTemplate.expire(tokenValue.getWaitingQCountName(), Duration.ofMinutes(10));
		return count;
	}
}
