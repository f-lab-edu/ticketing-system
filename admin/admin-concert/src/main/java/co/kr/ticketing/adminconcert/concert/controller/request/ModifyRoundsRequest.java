package co.kr.ticketing.adminconcert.concert.controller.request;

import java.time.LocalDateTime;
import java.util.List;

import co.kr.ticketing.adminconcert.concert.domain.model.Round;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ModifyRoundsRequest(
	@Valid
	@NotEmpty
	List<RoundResponse> rounds

) {
	public record RoundResponse(
		@NotNull
		LocalDateTime startDateTime
	) {
		public Round toModel() {
			return Round.builder()
				.startDateTime(startDateTime)
				.build();
		}
	}

	public List<Round> toModel() {
		return rounds.stream().map(RoundResponse::toModel).toList();
	}
}
