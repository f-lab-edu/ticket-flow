package github.ticketflow.domian.eventLocation.dto;

import github.ticketflow.domian.eventLocation.EventLocationEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EventLocationResponseDTO {

    private Long eventLocationId;
    private String eventLocationName;
    private int totalSeats;

    public EventLocationResponseDTO(EventLocationEntity entity) {
        this.eventLocationId = entity.getEventLocationId();
        this.eventLocationName = entity.getEventLocationName();
        this.totalSeats = entity.getTotalSeats();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventLocationResponseDTO dto = (EventLocationResponseDTO) o;
        return totalSeats == dto.totalSeats && Objects.equals(eventLocationId, dto.eventLocationId) && Objects.equals(eventLocationName, dto.eventLocationName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventLocationId, eventLocationName, totalSeats);
    }
}
