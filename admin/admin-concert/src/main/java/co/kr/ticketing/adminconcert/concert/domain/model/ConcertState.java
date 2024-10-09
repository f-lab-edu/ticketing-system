package co.kr.ticketing.adminconcert.concert.domain.model;

import lombok.Getter;

@Getter
public enum ConcertState {
	READY(true, true, true),
	OPEN(false, true, true),
	CLOSE(false, false, false);

	private final boolean setOpenTimeState;
	private final boolean setTicketingTimeState;
	private final boolean setRoundsState;

	ConcertState(boolean setOpenTimeState, boolean setTicketingTimeState, boolean setRoundsState) {
		this.setOpenTimeState = setOpenTimeState;
		this.setTicketingTimeState = setTicketingTimeState;
		this.setRoundsState = setRoundsState;
	}
}
