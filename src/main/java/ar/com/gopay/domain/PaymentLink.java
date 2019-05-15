package ar.com.gopay.domain;

import ar.com.gopay.domain.nosispayment.NosisData;
import ar.com.gopay.domain.nosispayment.NosisSms;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static ar.com.gopay.domain.PaymentLinkState.CA;
import static ar.com.gopay.domain.PaymentLinkState.PE;

@Entity
@Table(name = "payments_links")
public class PaymentLink {

    private static final int EXPIRATION = 120;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 36)
    private String token;

    private String description;

    @NotNull
    private Double amount;

    @JsonProperty("external_tx_id")
    @NotBlank
    @NotNull
    private String externalTxId;

    @OneToOne(targetEntity = Company.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private Company company;

    @JsonProperty("expiry_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT-03:00")
    private Date expiryDate;

    @Enumerated(EnumType.STRING)
    private PaymentLinkState state;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @JsonProperty("nosis_payment_data")
    @OneToMany(
            mappedBy = "paymentLink",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<NosisData> nosisData;

    @JsonProperty("nosis_sms")
    @OneToOne(mappedBy = "paymentLink", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private NosisSms nosisSms;

    @JsonProperty("created_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT-03:00")
    @CreatedDate
    private Date createdDate;

    @JsonProperty("is_token_expired")
    @Transient
    private boolean tokenExpired;

    @JsonProperty("fees_quantity")
    private Integer feesQuantity;

    public PaymentLink() {
    }

    public PaymentLink(String token, String description, Double amount, String externalTxId, Company company, PaymentLinkState state) {
        this.token = token;
        this.description = description;
        this.amount = amount;
        this.externalTxId = externalTxId;
        this.company = company;
        this.createdDate = new Date();
        this.expiryDate = calculateExpiryDate(EXPIRATION);
        this.state = state;
    }

    public void addNosisData(NosisData nosisData) {
        this.nosisData.add(nosisData);
        nosisData.setPaymentLink(this);
    }

    private Date calculateExpiryDate(final int expiryTimeInMinutes) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

    public void updateToken(final String token) {
        this.token = token;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    public boolean isTokenExpired() {
        return this.tokenExpired = this.expiryDate.getTime() - Calendar.getInstance().getTime().getTime() <= 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<NosisData> getNosisData() {
        return nosisData;
    }

    public void setNosisData(List<NosisData> nosisData) {
        this.nosisData = nosisData;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setTokenExpired(boolean tokenExpired) {
        this.tokenExpired = tokenExpired;
    }

    public PaymentLinkState getState() {

        if(isPending() && isTokenExpired()) {
            return CA;
        }
        return state;
    }

    public void setState(PaymentLinkState state) {
        this.state = state;
    }

    public Integer getFeesQuantity() {
        return feesQuantity;
    }

    public void setFeesQuantity(Integer feesQuantity) {
        this.feesQuantity = feesQuantity;
    }

    public NosisSms getNosisSms() {
        return nosisSms;
    }

    public void setNosisSms(NosisSms nosisSms) {
        if (nosisSms == null) {
            if (this.nosisSms != null) {
                this.nosisSms.setPaymentLink(null);
            }
        } else {
            nosisSms.setPaymentLink(this);
        }
        this.nosisSms = nosisSms;
    }

    public boolean isPending() {
        return state == PE;
    }

    @Override
    public String toString() {
        return "PaymentLink{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", amount=" + amount +
                ", externalTxId='" + externalTxId + '\'' +
                ", expiryDate=" + expiryDate +
                ", createdDate=" + createdDate +
                ", tokenExpired=" + tokenExpired +
                ", feesQuantity=" + feesQuantity +
                '}';
    }
}