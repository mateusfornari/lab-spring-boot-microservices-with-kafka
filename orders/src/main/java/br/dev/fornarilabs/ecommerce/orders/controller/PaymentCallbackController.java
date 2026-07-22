package br.dev.fornarilabs.ecommerce.orders.controller;

import br.dev.fornarilabs.ecommerce.orders.controller.dto.PaymentCallbackDTO;
import br.dev.fornarilabs.ecommerce.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders/payment-callback")
@RequiredArgsConstructor
public class PaymentCallbackController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<?> updatePaymentStatus(@RequestBody PaymentCallbackDTO body){
        orderService.updatePaymentStatus(
                body.correlationId(),
                body.paymentKey(),
                body.success(),
                body.notes()
        );
        return ResponseEntity.ok().build();
    }
}
