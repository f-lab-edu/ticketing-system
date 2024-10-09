package co.kr.ticketing.adminconcert.concert.domain.model;

import java.time.LocalDateTime;
import java.util.List;

import co.kr.ticketing.adminconcert.common.exception.BadRequestException;
import co.kr.ticketing.adminconcert.concert.controller.response.ConcertErrorResponseCode;
import co.kr.ticketing.adminconcert.place.domain.model.Place;
import lombok.Builder;

public record Concert(
	Long id,
	String name,
	String detailInfo,
	int runningTime,
	ConcertState state,
	LocalDateTime ticketingStartTime,
	LocalDateTime lastRunningEndTime,
	LocalDateTime openTime,
	List<Round> rounds,
	Place place,
	List<ConcertSeat> seats
) {
	@Builder
	public Concert(Long id, String name, String detailInfo, int runningTime, ConcertState state,
		LocalDateTime ticketingStartTime, LocalDateTime lastRunningEndTime, LocalDateTime openTime, List<Round> rounds,
		Place place, List<ConcertSeat> seats) {
		if (rounds.isEmpty()) {
			throw new BadRequestException(ConcertErrorResponseCode.CREATE_ROUNDS_ERROR.name(), "최소 1개의 콘서트 회차는 입력해주세요");
		}

		Round lastRound = rounds.get(rounds.size() - 1);
		this.lastRunningEndTime = lastRound.startDateTime().plusMinutes(runningTime);

		this.id = id;
		this.name = name;
		this.detailInfo = detailInfo;
		this.runningTime = runningTime;
		this.state = state;
		this.ticketingStartTime = ticketingStartTime;

		this.openTime = openTime;
		this.rounds = rounds;
		this.place = place;
		this.seats = seats;
	}

	public boolean isPossibleUpdate() {
		return !state.equals(ConcertState.CLOSE);
	}

	public Concert setOpenTime(LocalDateTime openTime) {
		if (!this.state.isSetOpenTimeState() ||
			openTime.isBefore(LocalDateTime.now()) ||
			(ticketingStartTime != null && ticketingStartTime.isBefore(openTime))) {

			throw new BadRequestException("오픈 시간을 설정할 수 없습니다. 다시 확인해주세요");
		}

		return Concert.builder()
			.id(this.id)
			.name(this.name)
			.detailInfo(this.detailInfo)
			.runningTime(this.runningTime)
			.state(this.state)
			.ticketingStartTime(this.ticketingStartTime)
			.lastRunningEndTime(this.lastRunningEndTime)
			.openTime(openTime)
			.rounds(List.copyOf(this.rounds))
			.place(this.place)
			.seats(List.copyOf(this.seats))
			.build();
	}

	public Concert setTicketingStartTime(LocalDateTime ticketingStartTime) {
		if (!this.state.isSetTicketingTimeState() ||
			ticketingStartTime.isBefore(LocalDateTime.now()) ||
			(openTime != null && this.ticketingStartTime.isBefore(openTime))) {

			throw new BadRequestException("예매 시작 시간을 설정할 수 없습니다. 다시 확인해주세요");
		}

		return Concert.builder()
			.id(this.id)
			.name(this.name)
			.detailInfo(this.detailInfo)
			.runningTime(this.runningTime)
			.state(this.state)
			.ticketingStartTime(ticketingStartTime)
			.lastRunningEndTime(this.lastRunningEndTime)
			.openTime(this.openTime)
			.rounds(List.copyOf(this.rounds))
			.place(this.place)
			.seats(List.copyOf(this.seats))
			.build();
	}

	public Concert setRounds(List<Round> rounds) {
		if (!this.state.isSetRoundsState()

			|| rounds.stream().map(Round::startDateTime).anyMatch(time -> time.isBefore(LocalDateTime.now()))

			|| (ticketingStartTime != null && rounds.stream()
			.map(Round::startDateTime)
			.anyMatch(time -> time.isBefore(ticketingStartTime)))

			|| (openTime != null && rounds.stream()
			.map(Round::startDateTime)
			.anyMatch(time -> time.isBefore(openTime)))
		) {
			throw new BadRequestException("콘서트 회차 정보를 수정할 수 없습니다. 다시 확인해주세요");
		}

		return Concert.builder()
			.id(this.id)
			.name(this.name)
			.detailInfo(this.detailInfo)
			.runningTime(this.runningTime)
			.state(this.state)
			.ticketingStartTime(this.ticketingStartTime)
			.lastRunningEndTime(this.lastRunningEndTime)
			.openTime(this.openTime)
			.rounds(List.copyOf(rounds))
			.place(this.place)
			.seats(List.copyOf(this.seats))
			.build();
	}

	public Concert updatePlace(Place place, List<ConcertSeat> seats) {
		return Concert.builder()
			.id(this.id)
			.name(this.name)
			.detailInfo(this.detailInfo)
			.runningTime(this.runningTime)
			.state(this.state)
			.ticketingStartTime(this.ticketingStartTime)
			.lastRunningEndTime(this.lastRunningEndTime)
			.openTime(this.openTime)
			.rounds(List.copyOf(rounds))
			.place(place)
			.seats(List.copyOf(seats))
			.build();
	}
}
