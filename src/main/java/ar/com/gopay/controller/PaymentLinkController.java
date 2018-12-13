package ar.com.gopay.controller;

import ar.com.gopay.domain.PaymentLink;
import ar.com.gopay.domain.PaymentLinkState;
import ar.com.gopay.exception.ResourceNotFoundException;
import ar.com.gopay.payload.PaymentLinkRequest;
import ar.com.gopay.payload.PaymentLinkResponse;
import ar.com.gopay.payload.PaymentLinkStateResponse;
import ar.com.gopay.security.UserPrincipal;
import ar.com.gopay.service.CompanyService;
import ar.com.gopay.service.PaymentLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static ar.com.gopay.domain.PaymentLinkState.CA;
import static ar.com.gopay.domain.PaymentLinkState.PE;

@RestController
@RequestMapping("/payment-link")
public class PaymentLinkController {

    @Value("${gopay.url}")
    private String goPayUrl;

    @Autowired
    private PaymentLinkService paymentLinkService;

    @Autowired
    private CompanyService companyService;

    @PostMapping
    public ResponseEntity<?> createPaymentLink(@Valid @RequestBody PaymentLinkRequest paymentLinkRequest) {

        UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String token = UUID.randomUUID().toString();

        PaymentLink link = paymentLinkService.createPaymentLink(new PaymentLink(token,
                paymentLinkRequest.getAmount(),
                paymentLinkRequest.getExternalTxId(),
                companyService.getById(user.getId()),
                PE));

        return ResponseEntity.ok(new PaymentLinkResponse(goPayUrl+ "payment-link", link.getId(), token));
    }

    @GetMapping("/tx-state/{externalTxId}")
    public ResponseEntity<?> state(@PathVariable("externalTxId") String externalTxId) {

        UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        PaymentLink paymentLink = paymentLinkService.getByExternalTxIdCompanyId(externalTxId, user.getId());

        if(paymentLink == null) {
            throw new ResourceNotFoundException(
                    String.format("No se ha encontrado la transaccion con id: %s", externalTxId)
            );
        }

        PaymentLinkState paymentLinkState = paymentLink.getState();

        if(paymentLink.getState().equals(PE) && paymentLink.isTokenExpired()) {
            paymentLinkState = CA;
        }

        return ResponseEntity.ok(
                new PaymentLinkStateResponse(paymentLink.getId(), paymentLinkState));
    }

}
