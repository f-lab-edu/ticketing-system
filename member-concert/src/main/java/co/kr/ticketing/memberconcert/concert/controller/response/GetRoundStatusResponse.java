package co.kr.ticketing.memberconcert.concert.controller.response;

import co.kr.ticketing.memberconcert.concert.domain.model.Round;
import lombok.Builder;

@Builder
public record GetRoundStatusResponse(
	Long roundId,
	int emptySeatCount
) {
	public static GetRoundStatusResponse from(Round round, int emptySeatCount) {
		return GetRoundStatusResponse.builder()
			.roundId(round.id())
			.emptySeatCount(emptySeatCount)
			.build();
	}
}
