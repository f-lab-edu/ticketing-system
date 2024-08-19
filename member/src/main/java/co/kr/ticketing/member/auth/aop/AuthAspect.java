package co.kr.ticketing.member.auth.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import co.kr.ticketing.member.auth.config.AuthConfig;
import co.kr.ticketing.member.auth.util.TokenUtil;
import co.kr.ticketing.member.exception.UnAuthorized;
import jakarta.servlet.http.Cookie;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthAspect {
	TokenUtil tokenUtil;

	@Before("@annotation(LoginCheck)")
	public void loginCheck(JoinPoint jp) {
		String token = getToken();
		if (!tokenUtil.isValidToken(token)) {
			throw new UnAuthorized();
		}
	}

	private String getToken() {
		Cookie[] cookies = ((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes())).getRequest()
			.getCookies();

		return cookies == null ? "" : Arrays.stream(cookies)
			.filter(cookie -> cookie.getName().equals(AuthConfig.LOGIN_COOKIE_NAME))
			.findAny()
			.orElseThrow(UnAuthorized::new)
			.getValue();
	}
}
