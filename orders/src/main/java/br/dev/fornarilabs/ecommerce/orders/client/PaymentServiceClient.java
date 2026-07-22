package br.dev.fornarilabs.ecommerce.orders.client;

import br.dev.fornarilabs.ecommerce.orders.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class PaymentServiceClient {

    public String requestPayment(Order order){
        log.info("Requesting payment for order ID: {}", order.getId());
        return UUID.randomUUID().toString();
    }
}
