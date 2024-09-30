package co.kr.ticketing.adminconcert.place.repository.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import co.kr.ticketing.adminconcert.place.domain.model.Seat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
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

	@Builder
	public SeatEntity(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, int columnNum, int rowNum,
		int floor, PlaceEntity placeEntity) {
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.columnNum = columnNum;
		this.rowNum = rowNum;
		this.floor = floor;
		setPlaceEntity(placeEntity);
	}

	public Seat toModel() {
		return Seat.builder()
			.id(id)
			.columnNum(columnNum)
			.rowNum(rowNum)
			.floor(floor)
			.build();
	}

	public static SeatEntity from(Seat seat) {
		return SeatEntity.builder()
			.columnNum(seat.columnNum())
			.rowNum(seat.rowNum())
			.floor(seat.floor())
			.build();
	}

	void setPlaceEntity(PlaceEntity placeEntity) {
		if (placeEntity != null) {
			this.placeEntity = placeEntity;
			if (!placeEntity.getSeatEntities().contains(this)) {
				placeEntity.getSeatEntities().add(this);
			}
		}
	}
}
