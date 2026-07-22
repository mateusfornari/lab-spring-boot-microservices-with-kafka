package br.dev.fornarilabs.ecommerce.orders.client.representation;

import java.math.BigDecimal;

public record ProductRepresentation(
        Long id,
        String name,
        BigDecimal unitAmount
) {
}
