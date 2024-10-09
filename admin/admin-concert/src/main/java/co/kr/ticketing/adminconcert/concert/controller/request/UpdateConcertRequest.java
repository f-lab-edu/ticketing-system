package co.kr.ticketing.adminconcert.concert.controller.request;

import java.util.List;

import co.kr.ticketing.adminconcert.concert.domain.model.Concert;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateConcertRequest(
	@NotBlank
	String name,
	String detailInfo,
	@NotNull
	int runningTime
) {
	public Concert toModel(Concert concert) {
		return Concert.builder()
			.id(concert.id())
			.name(name)
			.detailInfo(detailInfo)
			.runningTime(runningTime)
			.state(concert.state())
			.ticketingStartTime(concert.ticketingStartTime())
			.openTime(concert.openTime())
			.rounds(List.copyOf(concert.rounds()))
			.place(concert.place())
			.seats(List.copyOf(concert.seats()))
			.build();
	}
}
