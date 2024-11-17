package co.kr.ticketing.memberreservation.reservationtoken.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import co.kr.ticketing.memberreservation.reservationtoken.domain.model.ReservationTokenState;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ReservationTokenCheck {
	ReservationTokenState value();
}
