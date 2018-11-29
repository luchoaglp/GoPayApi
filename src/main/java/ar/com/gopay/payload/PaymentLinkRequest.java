package ar.com.gopay.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PaymentLinkRequest {

    @NotNull
    private Double amount;

    @NotNull
    @NotBlank
    @JsonProperty("external_tx_id")
    private String externalTxId;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getExternalTxId() {
        return externalTxId;
    }

    public void setExternalTxId(String externalTxId) {
        this.externalTxId = externalTxId;
    }
}
