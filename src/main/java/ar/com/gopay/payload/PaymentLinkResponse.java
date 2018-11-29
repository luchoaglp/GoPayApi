package ar.com.gopay.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentLinkResponse {

    @JsonProperty("tx_id")
    private Long txId;

    private String url;

    public PaymentLinkResponse(String url, Long linkId, String token) {
        this.txId = linkId;
        this.url = url + "/" + linkId + "/" + token;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getTxId() {
        return txId;
    }

    public void setTxId(Long txId) {
        this.txId = txId;
    }
}
