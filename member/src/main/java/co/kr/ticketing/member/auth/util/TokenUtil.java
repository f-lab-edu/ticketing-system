package co.kr.ticketing.member.auth.util;

import java.util.Map;

import org.springframework.stereotype.Component;

import co.kr.ticketing.member.auth.config.AuthConfig;
import co.kr.ticketing.member.domain.model.Member;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TokenUtil {
	JwtUtil jwtUtil;

	public String generateToken(Member member) {
		Map<String, Object> payload = Map.of(
			AuthConfig.PHONE_NUMBER, member.getPhoneNumber()
		);

		return jwtUtil.generateJwt(payload, AuthConfig.TOKEN_VALID_TIME);
	}

	public boolean isValidToken(String token) {
		return jwtUtil.isValidJwt(token);
	}
}
