package ar.com.gopay.repository;

import ar.com.gopay.domain.PaymentLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentLinkRepository extends JpaRepository<PaymentLink, Long> {

    PaymentLink findFirstByExternalTxId(String externalTxId);
    PaymentLink findByExternalTxIdAndCompanyId(String externalTxId, Long id);

    Boolean existsByExternalTxId(String externalTxId);
    Boolean existsByExternalTxIdAndCompanyId(String externalTxId, Long id);
}
