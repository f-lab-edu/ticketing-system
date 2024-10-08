package co.kr.ticketing.adminconcert.concert.domain.model;

import lombok.Getter;

@Getter
public enum CONCERT_STATE {
	READY(true, true),
	OPEN(false, true),
	CLOSE(false, false);

	private final boolean isSetOpenTimeState;
	private final boolean isSetTicketingTimeState;

	CONCERT_STATE(boolean isSetOpenTimeState, boolean isSetTicketingTimeState) {
		this.isSetOpenTimeState = isSetOpenTimeState;
		this.isSetTicketingTimeState = isSetTicketingTimeState;
	}
}
