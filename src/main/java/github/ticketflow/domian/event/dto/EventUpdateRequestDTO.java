package github.ticketflow.domian.event.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventUpdateRequestDTO {

    private Long eventLocationId;
    private String eventName;
    private String eventDescription;
    private LocalDate date;
    private LocalTime startTime;

}
