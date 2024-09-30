package co.kr.ticketing.adminconcert.place.repository.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import co.kr.ticketing.adminconcert.place.domain.infrastructure.dto.UpdatePlaceDto;
import co.kr.ticketing.adminconcert.place.domain.model.Place;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(
	uniqueConstraints = {
		@UniqueConstraint(name = "placeVersionUnique",
			columnNames = {"identifier", "version"}
		)
	})
@DynamicInsert
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class PlaceEntity {
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
	private String address;

	@Column(nullable = false)
	private String identifier;

	@Column(nullable = false)
	@ColumnDefault("0")
	private Long version;

	@Column(nullable = false)
	@ColumnDefault("false")
	private boolean last;

	@OneToMany(mappedBy = "placeEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private final List<SeatEntity> seatEntities = new ArrayList<>();

	@Builder
	public PlaceEntity(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, String name, String address,
		String identifier, Long version, boolean last, List<SeatEntity> seatEntities) {
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.name = name;
		this.address = address;
		this.identifier = identifier;
		this.version = version;
		this.last = last;
		setSeatEntities(seatEntities);
	}

	public Place toModel() {
		return Place.builder()
			.id(id)
			.name(name)
			.address(address)
			.identifier(identifier)
			.version(version)
			.last(last)
			.seats(seatEntities.stream().map(SeatEntity::toModel).toList())
			.build();
	}

	public static PlaceEntity from(Place place) {
		return PlaceEntity.builder()
			.name(place.name())
			.address(place.address())
			.identifier(place.identifier())
			.version(place.version())
			.last(place.last())
			.seatEntities(place.seats().stream().map(SeatEntity::from).toList())
			.build();
	}

	private void setSeatEntities(List<SeatEntity> seatEntities) {
		this.seatEntities.clear();
		this.seatEntities.addAll(seatEntities);
		seatEntities.forEach(seatEntity -> {
			if (seatEntity.getPlaceEntity() != this) {
				seatEntity.setPlaceEntity(this);
			}
		});
	}

	public SeatEntity getSeat(Long seatId) {
		return seatEntities.stream().filter(placeSeatEntity -> placeSeatEntity.getId().equals(seatId)).findAny()
			.orElse(null);
	}

	public void update(UpdatePlaceDto updateDto) {
		this.name = updateDto.name();
		this.address = updateDto.address();
		this.identifier = updateDto.identifier();
		this.version = updateDto.version();
		this.last = updateDto.last();
	}
}
