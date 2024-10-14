package co.kr.ticketing.adminconcert.concert.controller.request;

import co.kr.ticketing.adminconcert.concert.domain.model.ConcertState;
import co.kr.ticketing.adminconcert.concert.service.dto.GetConcertListVo;

public record GetConcertListRequest(
	String name,
	String placeName,
	ConcertState state
) {
	public GetConcertListVo toVo() {
		return GetConcertListVo.builder()
			.name(name)
			.placeName(placeName)
			.state(state)
			.build();
	}
}
