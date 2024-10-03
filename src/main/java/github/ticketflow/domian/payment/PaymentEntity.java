package github.ticketflow.domian.payment;

import github.ticketflow.domian.payment.dto.PaymentRequestDTO;
import github.ticketflow.domian.paymentTicket.PaymentTicketEntity;
import github.ticketflow.domian.user.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Payment")
@Builder
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Column(name = "number_of_ticket")
    private int numberOfTicket;

    @Column(name = "payment_price")
    private BigDecimal paymentPrice;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDate createdAt;

    @Column(name = "deleted_at")
    private LocalDate deletedAt;

    @OneToMany(mappedBy = "paymentEntity", fetch = FetchType.LAZY)
    private List<PaymentTicketEntity> paymentTickets;

    public PaymentEntity(PaymentVO paymentVO) {
        this.userEntity = paymentVO.getUserEntity();
        this.numberOfTicket = paymentVO.getNumberOfTicket();
        this.paymentPrice = paymentVO.getPaymentPrice();
    }

    public PaymentEntity update(LocalDate deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }

}
