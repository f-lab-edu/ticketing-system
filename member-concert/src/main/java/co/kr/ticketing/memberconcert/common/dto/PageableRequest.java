package co.kr.ticketing.memberconcert.common.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PageableRequest(
	@Positive
	@NotNull
	Integer page,
	@Positive
	@NotNull
	Integer limit
) {
	public Pageable toPageable() {
		return PageRequest.of(page - 1, limit);
	}
}
