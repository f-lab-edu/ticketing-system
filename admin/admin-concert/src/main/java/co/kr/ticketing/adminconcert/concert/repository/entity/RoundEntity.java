package co.kr.ticketing.adminconcert.concert.repository.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import co.kr.ticketing.adminconcert.concert.domain.model.Round;
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
@DynamicInsert
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class RoundEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@CreatedDate
	private LocalDateTime createdAt;

	@LastModifiedDate
	private LocalDateTime updatedAt;

	@Column(nullable = false)
	private LocalDateTime startDateTime;

	@ManyToOne(fetch = FetchType.LAZY)
	private ConcertEntity concertEntity;

	@Builder
	public RoundEntity(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime startDateTime,
		ConcertEntity concertEntity) {
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.startDateTime = startDateTime;
		setConcertEntity(concertEntity);
	}

	void setConcertEntity(ConcertEntity concertEntity) {
		if (concertEntity != null) {
			this.concertEntity = concertEntity;
			if (!concertEntity.getRoundEntities().contains(this)) {
				concertEntity.getRoundEntities().add(this);
			}
		}
	}

	public static RoundEntity from(ConcertEntity concertEntity, Round round) {
		return RoundEntity.builder()
			.startDateTime(round.startDateTime())
			.concertEntity(concertEntity)
			.build();
	}

	public Round toModel() {
		return Round.builder()
			.id(id)
			.startDateTime(startDateTime)
			.build();
	}
}
