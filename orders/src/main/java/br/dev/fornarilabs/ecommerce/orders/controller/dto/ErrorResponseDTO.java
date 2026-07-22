package br.dev.fornarilabs.ecommerce.orders.controller.dto;

public record ErrorResponseDTO (
        String message,
        String field,
        String error
) {
}
