package ar.com.gopay.payload;

public class PaymentLinkResponse {

    private String url;

    public PaymentLinkResponse(String url, String token) {
        this.url = url + "/" + token;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
