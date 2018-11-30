package ar.com.gopay.controller;

import ar.com.gopay.domain.PaymentLink;
import ar.com.gopay.domain.PaymentLinkState;
import ar.com.gopay.payload.PaymentLinkResponse;
import ar.com.gopay.payload.PaymentLinkRequest;
import ar.com.gopay.payload.PaymentLinkStateResponse;
import ar.com.gopay.security.UserPrincipal;
import ar.com.gopay.service.PaymentLinkService;
import ar.com.gopay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/payment-link")
public class PaymentLinkController {

    @Autowired
    private PaymentLinkService paymentLinkService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> createPaymentLink(@Valid @RequestBody PaymentLinkRequest paymentLinkRequest) {

        UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String token = UUID.randomUUID().toString();

        PaymentLink link = paymentLinkService.createPaymentLink(new PaymentLink(token,
                paymentLinkRequest.getAmount(),
                paymentLinkRequest.getExternalTxId(),
                userService.getCompanyById(user.getId())));

        return ResponseEntity.ok(new PaymentLinkResponse("http://181.170.81.225:8000/gopay/payment-link", link.getId(), token));
    }

    @GetMapping("/tx-state/{externalTxId}")
    public ResponseEntity<?> state(@PathVariable("externalTxId") String externalTxId) {

        UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        PaymentLink paymentLink = paymentLinkService.getByExternalTxIdCompanyId(externalTxId, user.getId());

        return ResponseEntity.ok(
                new PaymentLinkStateResponse(paymentLink.getId(), PaymentLinkState.PE));
    }

}
