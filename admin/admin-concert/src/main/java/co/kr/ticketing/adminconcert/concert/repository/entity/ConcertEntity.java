package co.kr.ticketing.adminconcert.concert.repository.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import co.kr.ticketing.adminconcert.concert.domain.model.CONCERT_STATE;
import co.kr.ticketing.adminconcert.concert.domain.model.Concert;
import co.kr.ticketing.adminconcert.place.repository.entity.PlaceEntity;
import jakarta.persistence.CascadeType;
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

	private Integer runningHour;
	private Integer runningMinute;

	@Column(nullable = false)
	@ColumnDefault("'READY'")
	@Enumerated(EnumType.STRING)
	private CONCERT_STATE state;

	private LocalDateTime ticketingStartTime;
	private LocalDateTime lastRunningEndTime;
	private LocalDateTime openTime;

	@ManyToOne(fetch = FetchType.LAZY)
	private PlaceEntity placeEntity;

	@OneToMany(mappedBy = "concertEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private final List<RoundEntity> roundEntities = new ArrayList<>();
	@OneToMany(mappedBy = "concertEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private final List<ConcertSeatEntity> seatEntities = new ArrayList<>();

	@Builder
	public ConcertEntity(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, String name, String detailInfo,
		Integer runningHour, Integer runningMinute, CONCERT_STATE state, LocalDateTime ticketingStartTime,
		LocalDateTime lastRunningEndTime, LocalDateTime openTime, PlaceEntity placeEntity,
		List<RoundEntity> roundEntities, List<ConcertSeatEntity> seatEntities) {
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.name = name;
		this.detailInfo = detailInfo;
		this.runningHour = runningHour;
		this.runningMinute = runningMinute;
		this.state = state;
		this.ticketingStartTime = ticketingStartTime;
		this.lastRunningEndTime = lastRunningEndTime;
		this.openTime = openTime;
		this.placeEntity = placeEntity;
		setRoundEntities(roundEntities);
		setSeatEntities(seatEntities);
	}

	public void setRoundEntities(List<RoundEntity> roundEntities) {
		this.roundEntities.clear();
		this.roundEntities.addAll(roundEntities);
		roundEntities.forEach(roundEntity -> {
			if (roundEntity.getConcertEntity() != this) {
				roundEntity.setConcertEntity(this);
			}
		});
	}

	public void setSeatEntities(List<ConcertSeatEntity> seatEntities) {
		this.seatEntities.clear();
		this.seatEntities.addAll(seatEntities);
		seatEntities.forEach(seatEntity -> {
			if (seatEntity.getConcertEntity() != this) {
				seatEntity.setConcertEntity(this);
			}
		});
	}

	public static ConcertEntity from(Concert concert, PlaceEntity placeEntity) {
		return ConcertEntity.builder()
			.name(concert.name())
			.detailInfo(concert.detailInfo())
			.runningHour(concert.runningHour())
			.runningMinute(concert.runningMinute())
			.state(concert.state())
			.ticketingStartTime(concert.ticketingStartTime())
			.lastRunningEndTime(concert.lastRunningEndTime())
			.openTime(concert.openTime())
			.placeEntity(placeEntity)
			.roundEntities(concert.rounds().stream().map(RoundEntity::from).toList())
			.seatEntities(concert.seats().stream().map(ConcertSeatEntity::from).toList())
			.build();
	}
}
