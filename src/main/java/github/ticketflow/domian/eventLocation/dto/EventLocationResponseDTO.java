package github.ticketflow.domian.eventLocation.dto;

import github.ticketflow.domian.eventLocation.EventLocationEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
}
