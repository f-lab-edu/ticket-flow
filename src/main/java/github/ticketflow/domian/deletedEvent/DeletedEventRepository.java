package github.ticketflow.domian.deletedEvent;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DeletedEventRepository extends JpaRepository<DeletedEventEntity, Long> {
}
