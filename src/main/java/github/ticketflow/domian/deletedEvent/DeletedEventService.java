package github.ticketflow.domian.deletedEvent;

import github.ticketflow.domian.event.EventEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletedEventService {

    private final DeletedEventRepository deletedEventRepository;

    public DeletedEventEntity createDeletedEvent(EventEntity eventEntity) {
        return deletedEventRepository.save(new DeletedEventEntity(eventEntity));
    }


}
