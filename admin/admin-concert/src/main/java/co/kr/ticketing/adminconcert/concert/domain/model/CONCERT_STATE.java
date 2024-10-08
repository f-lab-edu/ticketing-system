package co.kr.ticketing.adminconcert.concert.domain.model;

import lombok.Getter;

@Getter
public enum CONCERT_STATE {
	READY(true, true, true),
	OPEN(false, true, true),
	CLOSE(false, false, false);

	private final boolean isSetOpenTimeState;
	private final boolean isSetTicketingTimeState;
	private final boolean isSetRoundsState;

	CONCERT_STATE(boolean isSetOpenTimeState, boolean isSetTicketingTimeState, boolean isSetRoundsState) {
		this.isSetOpenTimeState = isSetOpenTimeState;
		this.isSetTicketingTimeState = isSetTicketingTimeState;
		this.isSetRoundsState = isSetRoundsState;
	}
}
