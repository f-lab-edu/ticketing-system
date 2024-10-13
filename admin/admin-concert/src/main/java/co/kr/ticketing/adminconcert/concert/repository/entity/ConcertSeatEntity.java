package co.kr.ticketing.adminconcert.concert.repository.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import co.kr.ticketing.adminconcert.concert.domain.model.ConcertSeat;
import co.kr.ticketing.adminconcert.concert.domain.model.ConcertSeatState;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@DynamicInsert
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ConcertSeatEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@CreatedDate
	private LocalDateTime createdAt;

	@LastModifiedDate
	private LocalDateTime updatedAt;

	@Column(nullable = false)
	private String grade;

	@Column(nullable = false)
	private Integer price;

	@Column(nullable = false)
	private Integer columnNum;

	@Column(nullable = false)
	private Integer rowNum;

	@Column(nullable = false)
	private Integer floor;

	@Column(nullable = false)
	@ColumnDefault("'EMPTY'")
	@Enumerated(EnumType.STRING)
	private ConcertSeatState state;

	@ManyToOne(fetch = FetchType.LAZY)
	private ConcertEntity concertEntity;

	@Builder
	public ConcertSeatEntity(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, String grade, Integer price,
		Integer columnNum, Integer rowNum, Integer floor, ConcertSeatState state, ConcertEntity concertEntity) {
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.grade = grade;
		this.price = price;
		this.columnNum = columnNum;
		this.rowNum = rowNum;
		this.floor = floor;
		this.state = state;
		this.concertEntity = concertEntity;
	}

	public static ConcertSeatEntity from(ConcertEntity concertEntity, ConcertSeat concertSeat) {
		return ConcertSeatEntity.builder()
			.grade(concertSeat.grade())
			.price(concertSeat.price())
			.columnNum(concertSeat.columnNum())
			.rowNum(concertSeat.rowNum())
			.floor(concertSeat.floor())
			.state(concertSeat.state())
			.concertEntity(concertEntity)
			.build();
	}

	public ConcertSeat toModel() {
		return ConcertSeat.builder()
			.id(id)
			.grade(grade)
			.price(price)
			.columnNum(columnNum)
			.rowNum(rowNum)
			.floor(floor)
			.state(state)
			.build();
	}
}
