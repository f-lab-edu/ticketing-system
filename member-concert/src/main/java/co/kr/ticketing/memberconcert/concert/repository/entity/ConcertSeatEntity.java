package co.kr.ticketing.memberconcert.concert.repository.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import co.kr.ticketing.memberconcert.concert.domain.model.ConcertSeatState;
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
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
}
