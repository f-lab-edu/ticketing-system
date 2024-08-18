package co.kr.ticketing.member.util.dto;

import lombok.Builder;

@Builder
public record CookieDto(
	String name,
	String value,
	boolean isEncode,
	int maxAge,
	boolean secure,
	boolean httpOnly,
	String domain,
	String sameSite,
	String path
) {
}
