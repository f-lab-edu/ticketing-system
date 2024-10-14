package co.kr.ticketing.adminconcert.concert.controller.request;

import java.util.List;

import co.kr.ticketing.adminconcert.concert.service.dto.ModifyConcertSeatVo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ModifyConcertSeatsRequest(
	@Valid
	@NotEmpty
	List<ConcertSeatRequest> seats
) {
	public record ConcertSeatRequest(
		@NotNull
		Long id,
		@NotBlank
		String grade,
		@NotNull
		Integer price
	) {
		public ModifyConcertSeatVo toVo() {
			return ModifyConcertSeatVo.builder()
				.id(id)
				.grade(grade)
				.price(price)
				.build();
		}
	}

	public List<ModifyConcertSeatVo> toVo() {
		return seats.stream().map(ConcertSeatRequest::toVo).toList();
	}
}
