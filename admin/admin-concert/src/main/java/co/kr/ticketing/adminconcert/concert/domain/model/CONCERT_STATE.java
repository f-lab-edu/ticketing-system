package co.kr.ticketing.adminconcert.concert.domain.model;

import lombok.Getter;

@Getter
public enum CONCERT_STATE {
	READY(true),
	OPEN(false),
	CLOSE(false);

	private final boolean isSetOpenTimeState;

	CONCERT_STATE(boolean isSetOpenTimeState) {
		this.isSetOpenTimeState = isSetOpenTimeState;
	}
}
