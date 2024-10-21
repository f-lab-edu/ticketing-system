package co.kr.ticketing.adminconcert.concert.repository.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import co.kr.ticketing.adminconcert.concert.domain.model.Concert;
import co.kr.ticketing.adminconcert.concert.domain.model.ConcertState;
import co.kr.ticketing.adminconcert.place.repository.entity.PlaceEntity;
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
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@DynamicInsert
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ConcertEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@CreatedDate
	private LocalDateTime createdAt;

	@LastModifiedDate
	private LocalDateTime updatedAt;

	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String detailInfo;

	private Integer runningTime;

	@Setter
	@Column(nullable = false)
	@ColumnDefault("'READY'")
	@Enumerated(EnumType.STRING)
	private ConcertState state;

	@Setter
	private LocalDateTime ticketingStartTime;
	private LocalDateTime lastRunningEndTime;
	@Setter
	private LocalDateTime openTime;

	@ManyToOne(fetch = FetchType.LAZY)
	private PlaceEntity placeEntity;

	@OneToMany(mappedBy = "concertEntity", fetch = FetchType.LAZY)
	private List<RoundEntity> roundEntities;
	@OneToMany(mappedBy = "concertEntity", fetch = FetchType.LAZY)
	private List<ConcertSeatEntity> seatEntities;

	@Builder
	public ConcertEntity(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, String name, String detailInfo,
		Integer runningTime, ConcertState state, LocalDateTime ticketingStartTime,
		LocalDateTime lastRunningEndTime, LocalDateTime openTime, PlaceEntity placeEntity,
		List<RoundEntity> roundEntities, List<ConcertSeatEntity> seatEntities) {
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.name = name;
		this.detailInfo = detailInfo;
		this.runningTime = runningTime;
		this.state = state;
		this.ticketingStartTime = ticketingStartTime;
		this.lastRunningEndTime = lastRunningEndTime;
		this.openTime = openTime;
		this.placeEntity = placeEntity;
		this.roundEntities = roundEntities;
		this.seatEntities = seatEntities;
	}

	public static ConcertEntity from(Concert concert, PlaceEntity placeEntity) {
		return ConcertEntity.builder()
			.name(concert.name())
			.detailInfo(concert.detailInfo())
			.runningTime(concert.runningTime())
			.state(concert.state())
			.ticketingStartTime(concert.ticketingStartTime())
			.lastRunningEndTime(concert.lastRunningEndTime())
			.openTime(concert.openTime())
			.placeEntity(placeEntity)
			.build();
	}

	public Concert toModel() {
		return Concert.builder()
			.id(id)
			.name(name)
			.detailInfo(detailInfo)
			.runningTime(runningTime)
			.state(state)
			.ticketingStartTime(ticketingStartTime)
			.lastRunningEndTime(lastRunningEndTime)
			.openTime(openTime)
			.place(placeEntity != null ? placeEntity.toModel() : null)
			.rounds(roundEntities.stream().map(RoundEntity::toModel).toList())
			.seats(seatEntities.stream().map(ConcertSeatEntity::toModel).toList())
			.build();
	}

	public void update(Concert concert) {
		this.name = concert.name();
		this.detailInfo = concert.detailInfo();
		this.runningTime = concert.runningTime();
		this.lastRunningEndTime = concert.lastRunningEndTime();
	}

	public void updatePlace(PlaceEntity placeEntity) {
		this.placeEntity = placeEntity;
	}
}
