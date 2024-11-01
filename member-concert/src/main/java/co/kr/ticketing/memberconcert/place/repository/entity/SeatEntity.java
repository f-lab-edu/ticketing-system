package co.kr.ticketing.memberconcert.place.repository.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import co.kr.ticketing.memberconcert.place.domain.model.Seat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class SeatEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@CreatedDate
	private LocalDateTime createdAt;

	@LastModifiedDate
	private LocalDateTime updatedAt;

	@Column(nullable = false)
	private int columnNum;
	@Column(nullable = false)
	private int rowNum;
	@Column(nullable = false)
	private int floor;

	@ManyToOne(fetch = FetchType.LAZY)
	private PlaceEntity placeEntity;

	public Seat toModel() {
		return Seat.builder()
			.id(id)
			.columnNum(columnNum)
			.rowNum(rowNum)
			.floor(floor)
			.build();
	}
}
