package co.kr.ticketing.adminconcert.concert.domain.model;

import lombok.Getter;

@Getter
public enum ConcertState {
	READY(true, true, true),
	OPEN(false, true, true),
	CLOSE(false, false, false);

	private final boolean setOpenTimeState;
	private final boolean isSetTicketingTimeState;
	private final boolean isSetRoundsState;

	ConcertState(boolean isSetOpenTimeState, boolean isSetTicketingTimeState, boolean isSetRoundsState) {
		this.setOpenTimeState = isSetOpenTimeState;
		this.isSetTicketingTimeState = isSetTicketingTimeState;
		this.isSetRoundsState = isSetRoundsState;
	}
}
