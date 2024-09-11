package github.ticketflow.domian.eventLocation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EventLocationUpdateRequestDTO {

    @NotBlank
    private String eventLocationName;
    @NotBlank
    private int totalSeats;

}
