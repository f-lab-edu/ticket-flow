package github.ticketflow.domian.eventLocation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EventLocationRequestDTO {

    @NotBlank
    private String eventLocationName;
    @NotNull
    private int totalSeats;

}
