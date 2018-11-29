package ar.com.gopay.service;

import ar.com.gopay.domain.PaymentLink;
import ar.com.gopay.repository.PaymentLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentLinkService {

    @Autowired
    private PaymentLinkRepository paymentLinkRepository;

    public PaymentLink createPaymentLink(PaymentLink paymentLink) {
        return paymentLinkRepository.save(paymentLink);
    }

    public List<PaymentLink> getAll() {
        return paymentLinkRepository.findAll();
    }
}
