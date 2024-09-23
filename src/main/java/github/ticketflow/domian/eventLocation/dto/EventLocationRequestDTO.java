package github.ticketflow.domian.eventLocation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EventLocationRequestDTO {

    @NotBlank
    private String eventLocationName;
    @Positive
    private int totalSeats;

}
