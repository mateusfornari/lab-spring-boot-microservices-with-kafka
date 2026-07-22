package br.dev.fornarilabs.ecommerce.orders.client.representation;

public record CustomerRepresentation (
        Long id,
        String name,
        String cpf,
        String address,
        String email,
        String phoneNumber
) {
}
