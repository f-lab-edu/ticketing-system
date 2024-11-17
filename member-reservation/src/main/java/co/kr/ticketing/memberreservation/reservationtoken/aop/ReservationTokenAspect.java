package co.kr.ticketing.memberreservation.reservationtoken.aop;

import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import co.kr.ticketing.memberreservation.common.exception.BadRequestException;
import co.kr.ticketing.memberreservation.reservationtoken.domain.model.ReservationTokenState;
import co.kr.ticketing.memberreservation.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReservationTokenAspect {
	@Before("@within(tokenCheck) || @annotation(tokenCheck)")
	public void loginCheck(JoinPoint jp, ReservationTokenCheck tokenCheck) {
		ReservationTokenState checkTokenState = getCheckTokenState(jp, tokenCheck);

		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String token = request.getParameter("token");

		validateToken(token, checkTokenState);
	}

	private ReservationTokenState getCheckTokenState(JoinPoint jp, ReservationTokenCheck tokenCheck) {
		if (tokenCheck == null) {
			ReservationTokenCheck annotation = jp.getTarget().getClass().getAnnotation(ReservationTokenCheck.class);
			if (annotation == null) {
				throw new BadRequestException("대기열 토큰이 존재하지 않습니다");
			}
			return annotation.value();
		}

		return tokenCheck.value();
	}

	private void validateToken(String token, ReservationTokenState checkState) {
		if (!JwtUtil.isValidJwt(token)) {
			throw new BadRequestException("잘못된 토큰입니다");
		} else {
			Map<String, Object> payload = JwtUtil.parsingJwt(token);
			if (payload.get("state") == null) {
				throw new BadRequestException("잘못된 토큰입니다");
			}

			ReservationTokenState state = (ReservationTokenState)payload.get("state");
			if (!state.equals(checkState)) {
				throw new BadRequestException("잘못된 토큰입니다");
			}
		}
	}
}
