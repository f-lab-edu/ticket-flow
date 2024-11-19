package github.ticketflow.domian.eventLocation;

import github.ticketflow.domian.eventLocation.dto.EventLocationRequestDTO;
import github.ticketflow.domian.eventLocation.dto.EventLocationResponseDTO;
import github.ticketflow.domian.eventLocation.dto.EventLocationUpdateRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EventLocation")
public class EventLocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_location_id")
    private Long eventLocationId;

    @Column(name = "event_location_name")
    private String eventLocationName;

    @Column(name = "total_seats")
    private int totalSeats;


    public EventLocationEntity(EventLocationRequestDTO dto) {
        this.eventLocationName = dto.getEventLocationName();
        this.totalSeats = dto.getTotalSeats();
    }

    public EventLocationEntity(EventLocationResponseDTO dto) {
        this.eventLocationId = dto.getEventLocationId();
        this.eventLocationName = dto.getEventLocationName();
        this.totalSeats = dto.getTotalSeats();
    }

    public EventLocationEntity update(EventLocationUpdateRequestDTO dto) {
        if (dto.getEventLocationName() != null) {
            this.eventLocationName = dto.getEventLocationName();
        }

        if (dto.getTotalSeats() != this.totalSeats) {
            this.totalSeats = dto.getTotalSeats();
        }

        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventLocationEntity that = (EventLocationEntity) o;
        return Objects.equals(eventLocationId, that.eventLocationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventLocationId);
    }
}
