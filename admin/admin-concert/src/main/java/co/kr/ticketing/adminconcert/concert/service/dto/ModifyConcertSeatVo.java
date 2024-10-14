package co.kr.ticketing.adminconcert.concert.service.dto;

import lombok.Builder;

@Builder
public record ModifyConcertSeatVo(
	Long id,
	String grade,
	Integer price
) {
}
