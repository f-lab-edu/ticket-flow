package github.ticketflow.domian.event;

import github.ticketflow.domian.event.dto.EventRequestDTO;
import github.ticketflow.domian.event.dto.EventUpdateRequestDTO;
import github.ticketflow.domian.event.entity.EventEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;

    @GetMapping("/{eventId}")
    public ResponseEntity<EventEntity> getEventById(@PathVariable Long eventId) {
        return ResponseEntity.ok(eventService.getEventById(eventId));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<EventEntity>> getEventByCategoryId(@PathVariable Long categoryId,
                                                                  @RequestParam(required = false, defaultValue = "0", value = "page") int pageNo) {
        return ResponseEntity.ok(eventService.getEventByCategoryId(categoryId, pageNo));
    }

    @PostMapping
    public ResponseEntity<EventEntity> createEvent(@Valid @RequestBody EventRequestDTO dto) {
        return ResponseEntity.ok(eventService.createEvent(dto));
    }

    @PatchMapping("/{eventId}")
    public ResponseEntity<EventEntity> updateEvent(@PathVariable Long eventId,
                                                    @Valid @RequestBody EventUpdateRequestDTO dto) {
        return ResponseEntity.ok(eventService.updateEvent(eventId, dto));
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<EventEntity> deleteEvent(@PathVariable Long eventId) {
        return ResponseEntity.ok(eventService.deletedEvent(eventId));
    }
}
