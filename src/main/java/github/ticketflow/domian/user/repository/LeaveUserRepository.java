package github.ticketflow.domian.user.repository;

import github.ticketflow.domian.user.entity.LeaveUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveUserRepository extends JpaRepository<LeaveUserEntity, Long> {
}
