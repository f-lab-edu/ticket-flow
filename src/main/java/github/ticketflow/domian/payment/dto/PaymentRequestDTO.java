package github.ticketflow.domian.payment.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequestDTO {

    @NotNull
    private Long userId;
    @Positive
    private int numberOfTicket;
    @NotNull
    private List<Long> ticketIds;
    @NotNull
    private BigDecimal paymentPrice;

}
