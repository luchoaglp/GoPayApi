package ar.com.gopay.service;

import ar.com.gopay.domain.PaymentLink;
import ar.com.gopay.repository.PaymentLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentLinkService {

    @Autowired
    private PaymentLinkRepository paymentLinkRepository;

    public void createPaymentLink(PaymentLink paymentLink) {
        paymentLinkRepository.save(paymentLink);
    }
}
