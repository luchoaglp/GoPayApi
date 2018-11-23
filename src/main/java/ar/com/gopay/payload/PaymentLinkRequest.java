package ar.com.gopay.payload;

import javax.validation.constraints.NotNull;

public class PaymentLinkRequest {

    @NotNull
    private Double amount;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
