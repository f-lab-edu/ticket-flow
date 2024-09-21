package github.ticketflow.domian.event.repository;

import github.ticketflow.domian.event.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventEntity, Long> {
}
