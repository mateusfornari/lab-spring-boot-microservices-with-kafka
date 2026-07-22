package br.dev.fornarilabs.ecommerce.orders.controller.dto;

import br.dev.fornarilabs.ecommerce.orders.domain.Order;
import br.dev.fornarilabs.ecommerce.orders.domain.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record CreateOrderDTO (
        Long customerId,
        PaymentDataDTO paymentData,
        List<OrderItemDTO> items
) {
    public Order toOrder(){
        Order order = new Order();
        order.setCustomerId(customerId);
        order.setPaymentData(paymentData.toPaymentData());
        order.setItems(items.stream().map(OrderItemDTO::toOrderItem).toList());
        var totalAmount = calculateTotalAmount(order);
        order.setTotalAmount(totalAmount);
        order.setStatus(OrderStatus.CREATED);
        order.setOrderTime(LocalDateTime.now());
        order.getItems().forEach(item -> item.setOrder(order));
        return order;
    }

    private BigDecimal calculateTotalAmount(Order order) {
        return order.getItems().stream().map(
            item -> item.getUnitAmount().multiply(BigDecimal.valueOf(item.getQuantity()))
        )
        .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
