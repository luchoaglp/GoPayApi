package ar.com.gopay.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PaymentLinkRequest {

    @NotNull(message = "{amount.notnull}")
    private Double amount;

    private String description;

    @NotNull(message = "{externalTxId.notnull}")
    @NotBlank(message = "{externalTxId.notblank}")
    @JsonProperty("external_tx_id")
    private String externalTxId;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExternalTxId() {
        return externalTxId;
    }

    public void setExternalTxId(String externalTxId) {
        this.externalTxId = externalTxId;
    }

}
