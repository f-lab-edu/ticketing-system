package co.kr.ticketing.adminconcert.concert.domain.model;

import java.time.LocalDateTime;
import java.util.List;

import co.kr.ticketing.adminconcert.common.exception.BadRequestException;
import co.kr.ticketing.adminconcert.place.domain.model.Place;
import lombok.Builder;

@Builder
public record Concert(
	Long id,
	String name,
	String detailInfo,
	int runningHour,
	int runningMinute,
	CONCERT_STATE state,
	LocalDateTime ticketingStartTime,
	LocalDateTime lastRunningEndTime,
	LocalDateTime openTime,
	List<Round> rounds,
	Place place,
	List<ConcertSeat> seats
) {
	public boolean isPossibleUpdate() {
		return !state.equals(CONCERT_STATE.CLOSE);
	}

	public Concert setOpenTime(LocalDateTime openTime) {
		if (!this.state.isSetOpenTimeState() ||
			openTime.isBefore(LocalDateTime.now()) ||
			(ticketingStartTime != null && ticketingStartTime.isBefore(openTime))) {
			
			throw new BadRequestException("오픈 시간을 설정할 수 없습니다");
		}

		return Concert.builder()
			.id(this.id)
			.name(this.name)
			.detailInfo(this.detailInfo)
			.runningHour(this.runningHour)
			.runningMinute(this.runningMinute)
			.state(this.state)
			.ticketingStartTime(this.ticketingStartTime)
			.lastRunningEndTime(this.lastRunningEndTime)
			.openTime(openTime)
			.rounds(this.rounds)
			.place(this.place)
			.seats(List.copyOf(seats))
			.build();
	}
}
