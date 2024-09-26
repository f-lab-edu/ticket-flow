package github.ticketflow.domian.event;

import github.ticketflow.domian.event.dto.EventRequestDTO;
import github.ticketflow.domian.event.dto.EventResponseDTO;
import github.ticketflow.domian.event.dto.EventUpdateRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event")
public class EventController {

    private final EventFacade eventFacade;

    @GetMapping("/{eventId}")
    public ResponseEntity<EventResponseDTO> getEventById(@PathVariable Long eventId) {
        return ResponseEntity.ok(eventFacade.getEventById(eventId));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<EventResponseDTO>> getEventByCategoryId(@PathVariable Long categoryId,
                                                                  @RequestParam(required = false, defaultValue = "0", value = "page") int pageNo) {
        return ResponseEntity.ok(eventFacade.getEventByCategoryId(categoryId, pageNo));
    }

    @PostMapping
    public ResponseEntity<EventResponseDTO> createEvent(@Valid @RequestBody EventRequestDTO dto) {
        return ResponseEntity.ok(eventFacade.createEvent(dto));
    }

    @PatchMapping("/{eventId}")
    public ResponseEntity<EventResponseDTO> updateEvent(@PathVariable Long eventId,
                                                    @Valid @RequestBody EventUpdateRequestDTO dto) {
        return ResponseEntity.ok(eventFacade.updateEvent(eventId, dto));
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<EventResponseDTO> deleteEvent(@PathVariable Long eventId) {
        return ResponseEntity.ok(eventFacade.deletedEvent(eventId));
    }
}
