package github.ticketflow.domian.event.dto;

import github.ticketflow.domian.event.entity.EventEntity;
import github.ticketflow.domian.eventLocation.dto.EventLocationResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EventResponseDTO {

    private Long eventId;
    private EventLocationResponseDTO eventLocationResponseDTO;
    private String eventName;
    private String eventDescription;
    private LocalDate date;
    private LocalTime start_time;
    private LocalDate createAt;
    private LocalDate modifyAt;

    public EventResponseDTO(EventEntity eventEntity) {
        this.eventId = eventEntity.getEventId();
        this.eventLocationResponseDTO = new EventLocationResponseDTO(eventEntity.getEventLocation());
        this.eventName = eventEntity.getEventName();
        this.eventDescription = eventEntity.getEventDescription();
        this.date = eventEntity.getDate();
        this.start_time = eventEntity.getStartTime();
        this.createAt = eventEntity.getCreatedAt();
        this.modifyAt = eventEntity.getModifiedAt();
    }
}
