package br.dev.fornarilabs.ecommerce.orders.domain;

import br.dev.fornarilabs.ecommerce.orders.domain.enums.PaymentType;
import lombok.Data;

@Data
public class PaymentData {
    private String data;
    private PaymentType paymentType;
}
