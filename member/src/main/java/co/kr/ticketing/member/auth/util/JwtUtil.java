package co.kr.ticketing.member.auth.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import co.kr.ticketing.member.auth.config.AuthConfig;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtUtil {

	public String generateAccessToken(Map<String, Object> valueMap, int expireSeconds) {
		return generateToken(valueMap, expireSeconds, AuthConfig.SECRET_KEY);
	}

	private String generateToken(Map<String, Object> valueMap, int expireSeconds, String secretKey) {
		Map<String, Object> headers = new HashMap<>();
		headers.put("typ", "JWT");
		headers.put("alg", "HS256");

		Map<String, Object> payload = new HashMap<>();
		payload.putAll(valueMap);

		return Jwts.builder()
			.setHeader(headers)
			.setClaims(payload)
			.setIssuedAt(
				Date.from(
					ZonedDateTime.now().toInstant()
				))
			.setExpiration(
				getDate(expireSeconds)
			)
			.signWith(SignatureAlgorithm.HS256, secretKey.getBytes()).compact();
	}

	private Date getDate(int seconds) {
		LocalDateTime localDateTime = LocalDateTime.now().plusSeconds(seconds);
		return Date.from(
			localDateTime.atZone(
					ZoneId.systemDefault()
				)
				.toInstant()
		);
	}
}
