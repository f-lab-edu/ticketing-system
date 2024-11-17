package co.kr.ticketing.memberreservation.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JwtUtil {
	private final String SECRET_KEY = "waiting_token_secret_key";

	public String generateJwt(Map<String, Object> valueMap, int expireSeconds) {
		return generateToken(valueMap, expireSeconds, SECRET_KEY);
	}

	public Map<String, Object> parsingJwt(String token) throws JwtException {
		return Jwts.parser()
			.setSigningKey(SECRET_KEY.getBytes())
			.parseClaimsJws(token)
			.getBody();
	}

	public boolean isValidJwt(String jwt) {
		if (jwt == null || jwt.isEmpty()) {
			return false;
		}

		try {
			Jwts.parser()
				.setSigningKey(SECRET_KEY.getBytes())
				.parseClaimsJws(jwt)
				.getBody();
		} catch (JwtException e) {
			return false;
		}

		return true;
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
