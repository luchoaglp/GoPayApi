package ar.com.gopay.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JwtAuthenticationResponse {

    @JsonProperty("token_type")
    private String tokenType = "Bearer";

    @JsonProperty("access_token")
    private String accessToken;

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
