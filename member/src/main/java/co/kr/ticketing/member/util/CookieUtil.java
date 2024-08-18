package co.kr.ticketing.member.util;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import co.kr.ticketing.member.util.dto.CookieDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CookieUtil {
	public ResponseCookie createTokenCookie(CookieDto cookieDto) {
		String encodeStr = cookieDto.value();

		if (cookieDto.isEncode()) {
			encodeStr = URLEncoder.encode(encodeStr, StandardCharsets.UTF_8);
		}

		return ResponseCookie.from(cookieDto.name(), encodeStr)
			.maxAge(cookieDto.maxAge())
			.path(cookieDto.path())
			.httpOnly(cookieDto.httpOnly())
			.secure(cookieDto.secure())
			.sameSite(cookieDto.sameSite())
			.domain(cookieDto.domain())
			.build();
	}
}
