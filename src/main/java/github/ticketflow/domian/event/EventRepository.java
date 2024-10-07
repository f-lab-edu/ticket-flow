package github.ticketflow.domian.event;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<EventEntity, Long> {

    List<EventEntity> findAllByEventNameContainingIgnoreCase(String eventName);

}
