package co.kr.ticketing.memberconcert.common.dto;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Builder;

@Builder
public record PageResponse<T>(
	PageInfo pageInfo,
	List<T> contents
) {
	@Builder
	public record PageInfo(
		Long totalItems,
		int itemsPerPage,
		int totalPages,
		int currentPage
	) {
		public static <T> PageInfo from(Page<T> items) {
			return PageInfo.builder()
				.totalItems(items.getTotalElements())
				.itemsPerPage(items.getSize())
				.totalPages(items.getTotalPages())
				.currentPage(items.getNumber() + 1)
				.build();
		}
	}

	public static <T, R> PageResponse<R> from(Page<T> items, List<R> responseItems) {
		return PageResponse.<R>builder()
			.pageInfo(PageInfo.from(items))
			.contents(List.copyOf(responseItems))
			.build();
	}
}
