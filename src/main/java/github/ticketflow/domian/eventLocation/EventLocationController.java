package github.ticketflow.domian.eventLocation;

import github.ticketflow.domian.eventLocation.dto.EventLocationRequestDTO;
import github.ticketflow.domian.eventLocation.dto.EventLocationResponseDTO;
import github.ticketflow.domian.eventLocation.dto.EventLocationUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event-location")
public class EventLocationController {

    private final EventLocationService eventLocationService;

    @GetMapping("/{eventLocationId}")
    public ResponseEntity<EventLocationResponseDTO> getEventLocationById(@PathVariable Long eventLocationId) {
        return ResponseEntity.ok(eventLocationService.getEventLocation(eventLocationId));
    }

    @PostMapping()
    public ResponseEntity<EventLocationResponseDTO> createEventLocation(@RequestBody EventLocationRequestDTO dto) {
        return ResponseEntity.ok(eventLocationService.createEventLocation(dto));
    }

    @PatchMapping("/{eventLocationId}")
    public ResponseEntity<EventLocationResponseDTO> updateEventLocation(@PathVariable Long eventLocationId,
                                                                        @RequestBody EventLocationUpdateRequestDTO dto) {
        return ResponseEntity.ok(eventLocationService.updateEventLocation(eventLocationId, dto));
    }

    @DeleteMapping("/{eventLocationId}")
    public ResponseEntity<EventLocationResponseDTO> deleteEventLocation(@PathVariable Long eventLocationId) {
        return ResponseEntity.ok(eventLocationService.deletedEventLocation(eventLocationId));
    }

}
