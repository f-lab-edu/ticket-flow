package github.ticketflow.domian.event.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class EventRequestDTO {

    @NotNull
    private Long eventLocationId;
    @NotNull
    private Long categoryId;
    @NotBlank
    private String eventName;
    @NotBlank
    private String eventDescription;
    @NotNull
    private LocalDate date;
    @NotNull
    private LocalTime startTime;

}
