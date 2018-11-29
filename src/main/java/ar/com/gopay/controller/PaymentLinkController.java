package ar.com.gopay.controller;

import ar.com.gopay.domain.PaymentLink;
import ar.com.gopay.payload.PaymentLinkResponse;
import ar.com.gopay.payload.PaymentLinkRequest;
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
public class PaymentLinkController {

    @Autowired
    private PaymentLinkService paymentLinkService;

    @Autowired
    private UserService userService;

    @PostMapping("/payment-link")
    public ResponseEntity<?> createPaymentLink(@Valid @RequestBody PaymentLinkRequest paymentLinkRequest) {

        UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String token = UUID.randomUUID().toString();

        PaymentLink link = paymentLinkService.createPaymentLink(new PaymentLink(token,
                paymentLinkRequest.getAmount(),
                userService.getCompanyById(user.getId())));

        return ResponseEntity.ok(new PaymentLinkResponse("http://localhost:8000/gopay/payment-link", link.getId(), token));
    }

}
