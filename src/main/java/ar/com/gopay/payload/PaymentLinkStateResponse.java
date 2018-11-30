package ar.com.gopay.payload;

import ar.com.gopay.domain.PaymentLinkState;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentLinkStateResponse {

    @JsonProperty("tx_id")
    private Long txId;

    private PaymentLinkState state;

    public PaymentLinkStateResponse(Long txId, PaymentLinkState state) {
        this.txId = txId;
        this.state = state;
    }

    public Long getTxId() {
        return txId;
    }

    public void setTxId(Long txId) {
        this.txId = txId;
    }

    public PaymentLinkState getState() {
        return state;
    }

    public void setState(PaymentLinkState state) {
        this.state = state;
    }
}
