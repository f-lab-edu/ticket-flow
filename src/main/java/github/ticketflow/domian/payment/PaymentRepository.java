package github.ticketflow.domian.payment;

import github.ticketflow.domian.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {

    List<PaymentEntity> findByUserEntity(UserEntity userEntity);

}
