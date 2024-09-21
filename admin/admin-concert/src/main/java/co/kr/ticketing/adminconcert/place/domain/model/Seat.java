package co.kr.ticketing.adminconcert.place.domain.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
public class Seat {
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
	private Place place;

	@Builder
	public Seat(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, int columnNum, int rowNum,
		int floor, Place place) {
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.columnNum = columnNum;
		this.rowNum = rowNum;
		this.floor = floor;
		setPlace(place);
	}

	void setPlace(Place place) {
		if (place != null) {
			this.place = place;
			if (!place.getSeats().contains(this)) {
				place.getSeats().add(this);
			}
		}
	}
}
