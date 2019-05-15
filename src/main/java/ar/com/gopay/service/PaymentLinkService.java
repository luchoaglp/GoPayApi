package ar.com.gopay.service;

import ar.com.gopay.domain.PaymentLink;
import ar.com.gopay.repository.PaymentLinkRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentLinkService {

    private final PaymentLinkRepository paymentLinkRepository;

    public PaymentLinkService(PaymentLinkRepository paymentLinkRepository) {
        this.paymentLinkRepository = paymentLinkRepository;
    }

    public PaymentLink createPaymentLink(PaymentLink paymentLink) {
        return paymentLinkRepository.save(paymentLink);
    }

    public List<PaymentLink> getAll() {
        return paymentLinkRepository.findAll();
    }

    public PaymentLink getFirstByExternalTxId(String externalTxId) {
        return paymentLinkRepository.findFirstByExternalTxId(externalTxId);
    }

    public Boolean existsByExternalTxIdAndCompanyId(String externalTxId, Long id) {
        return paymentLinkRepository.existsByExternalTxIdAndCompanyId(externalTxId, id);
    }

    public PaymentLink getByExternalTxIdAndCompanyId(String externalTxId, Long id) {
        return paymentLinkRepository.findByExternalTxIdAndCompanyId(externalTxId, id);
    }

}
