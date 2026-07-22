package br.dev.fornarilabs.ecommerce.orders.controller.dto;

import br.dev.fornarilabs.ecommerce.orders.domain.OrderItem;

import java.math.BigDecimal;

public record OrderItemDTO(
        Long productId,
        Integer quantity,
        BigDecimal unitAmount
) {
    public OrderItem toOrderItem(){
        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(productId);
        orderItem.setQuantity(quantity);
        orderItem.setUnitAmount(unitAmount);
        return orderItem;
    }
}
