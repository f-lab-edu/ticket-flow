package github.ticketflow.domian.event.repository;

import github.ticketflow.domian.event.entity.DeletedEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeletedEventRepository extends JpaRepository<DeletedEventEntity, Long> {
}
