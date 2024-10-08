package co.kr.ticketing.adminconcert.concert.controller.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public record SetOpenTimeRequest(
	@NotNull
	LocalDateTime openTime
) {
}
