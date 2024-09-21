package co.kr.ticketing.adminconcert.place.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Place {
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

	@OneToMany(mappedBy = "place", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private final List<Seat> seats = new ArrayList<>();

	@Builder
	public Place(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, String name, String address,
		List<Seat> seats) {
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.name = name;
		this.address = address;
		setSeats(seats);
	}

	private void setSeats(List<Seat> seats) {
		this.seats.clear();
		this.seats.addAll(seats);
		seats.forEach(seat -> {
			if (seat.getPlace() != this) {
				seat.setPlace(this);
			}
		});
	}

	public Seat getSeat(Long seatId) {
		return seats.stream().filter(placeSeat -> placeSeat.getId().equals(seatId)).findAny()
			.orElse(null);
	}
}
