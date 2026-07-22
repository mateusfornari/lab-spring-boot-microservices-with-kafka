package br.dev.fornarilabs.ecommerce.orders.controller.dto;

public record PaymentCallbackDTO(
        Long correlationId,
        String paymentKey,
        boolean success,
        String notes
) {
}
