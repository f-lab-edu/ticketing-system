package co.kr.ticketing.memberreservation.reservationtoken.infrastructure;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import co.kr.ticketing.memberreservation.reservationtoken.domain.infrastructure.ReservationTokenGenerator;
import co.kr.ticketing.memberreservation.reservationtoken.domain.model.ReservationToken;
import co.kr.ticketing.memberreservation.reservationtoken.domain.model.ReservationTokenValue;
import co.kr.ticketing.memberreservation.util.JwtUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReservationTokenJwtGenerator implements ReservationTokenGenerator {

	@Override
	public ReservationToken generate(ReservationTokenValue tokenValue) {
		int EXPIRE_SECONDS = 600; //10m

		return ReservationToken.builder()
			.tokenKey(JwtUtil.generateJwt(createJwtPayload(tokenValue), EXPIRE_SECONDS))
			.value(tokenValue)
			.build();
	}

	private Map<String, Object> createJwtPayload(ReservationTokenValue tokenValue) {
		Map<String, Object> map = new HashMap<>();

		map.put("id", tokenValue.id());
		map.put("sequenceNumber", tokenValue.sequenceNumber());
		map.put("type", tokenValue.type().name());

		return map;
	}
}