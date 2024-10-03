package github.ticketflow.domian.payment;

import github.ticketflow.config.exception.GlobalCommonException;
import github.ticketflow.config.exception.payment.PaymentErrorResponsive;
import github.ticketflow.domian.payment.dto.PaymentRequestDTO;
import github.ticketflow.domian.payment.dto.PaymentResponseDTO;
import github.ticketflow.domian.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentEntity getPaymentById(Long paymentId) {
        return paymentRepository.findById(paymentId).orElseThrow(()->
                new GlobalCommonException(PaymentErrorResponsive.NOT_FOUND_PAYMENT));
    }

    public List<PaymentResponseDTO> getPaymentByUserEntity(UserEntity userEntity) {
        List<PaymentResponseDTO> paymentResponseDTOS = new ArrayList<>();

        paymentRepository.findByUserEntity(userEntity).forEach(paymentEntity -> {
            PaymentResponseDTO paymentResponseDTO = new PaymentResponseDTO(paymentEntity);
            paymentResponseDTOS.add(paymentResponseDTO);
        });

        return paymentResponseDTOS;
    }

    public PaymentEntity createPayment(PaymentVO paymentVO) {
        PaymentEntity paymentEntity = new PaymentEntity(paymentVO);
        return paymentRepository.save(paymentEntity);
    }

    public PaymentEntity updatePayment(Long paymentId) {
        PaymentEntity paymentEntity = getPaymentById(paymentId);
        LocalDate deletedAt = LocalDate.now();
        paymentEntity.update(deletedAt);

        return paymentRepository.save(paymentEntity);
    }

    public PaymentEntity deletedPayment(Long paymentId) {
        PaymentEntity paymentEntity = getPaymentById(paymentId);
        paymentRepository.delete(paymentEntity);

        return paymentEntity;
    }


}
