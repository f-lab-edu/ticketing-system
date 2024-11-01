package co.kr.ticketing.memberconcert.concert.repository.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import co.kr.ticketing.memberconcert.concert.domain.model.Concert;
import co.kr.ticketing.memberconcert.concert.domain.model.ConcertState;
import co.kr.ticketing.memberconcert.place.repository.entity.PlaceEntity;
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Entity
@Getter
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
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
			.build();
	}
}
