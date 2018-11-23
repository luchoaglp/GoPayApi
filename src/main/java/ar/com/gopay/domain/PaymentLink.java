package ar.com.gopay.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Calendar;
import java.util.Date;

@Entity
public class PaymentLink {

    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(max = 36)
    private String token;

    private Double amount;

    @OneToOne(targetEntity = Company.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private Company company;

    private Date expiryDate;

    public PaymentLink() {
    }

    public PaymentLink(final String token, Double amount) {
        this.token = token;
        this.amount = amount;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    public PaymentLink(final String token, Double amount, Company company) {
        this.token = token;
        this.amount = amount;
        this.company = company;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(final String token) {
        this.token = token;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(final Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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

    @Override
    public String toString() {
        return "PaymentLink{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", amount=" + amount +
                ", company=" + company +
                ", expiryDate=" + expiryDate +
                '}';
    }
}