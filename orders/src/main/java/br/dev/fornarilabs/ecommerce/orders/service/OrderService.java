package br.dev.fornarilabs.ecommerce.orders.service;

import br.dev.fornarilabs.ecommerce.orders.client.PaymentServiceClient;
import br.dev.fornarilabs.ecommerce.orders.domain.Order;
import br.dev.fornarilabs.ecommerce.orders.domain.PaymentData;
import br.dev.fornarilabs.ecommerce.orders.domain.enums.OrderStatus;
import br.dev.fornarilabs.ecommerce.orders.domain.enums.PaymentType;
import br.dev.fornarilabs.ecommerce.orders.domain.exception.ItemNotFoundException;
import br.dev.fornarilabs.ecommerce.orders.repository.OrderItemRepository;
import br.dev.fornarilabs.ecommerce.orders.repository.OrderRepository;
import br.dev.fornarilabs.ecommerce.orders.validator.OrderValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderValidator orderValidator;
    private final PaymentServiceClient paymentServiceClient;

    @Transactional
    public Order create(Order order){
        orderValidator.validate(order);
        persistOrder(order);
        sendPaymentRequest(order);
        return order;
    }

    public void updatePaymentStatus(Long orderId, String paymentKey, boolean success, String notes){
        var found = orderRepository.findByIdAndPaymentKey(orderId, paymentKey);
        if(found.isEmpty()){
            log.error("Order not found by ID {} and payment key {}", orderId, paymentKey);
            return;
        }
        Order order = found.get();
        if(success){
            log.info("Payment succeeded.");
            order.setStatus(OrderStatus.PAID);
        }else{
            log.info("Payment not succeeded.");
            order.setStatus(OrderStatus.PAYMENT_ERROR);
        }
        order.setNotes(notes);
        orderRepository.save(order);
    }

    public void addPayment(Long orderId, String paymentData, PaymentType paymentType){
        var found = orderRepository.findById(orderId);
        if(found.isEmpty()){
            throw new ItemNotFoundException("Order not found.");
        }
        PaymentData pmtData = new PaymentData();
        pmtData.setData(paymentData);
        pmtData.setPaymentType(paymentType);

        Order order = found.get();

        order.setPaymentData(pmtData);
        order.setStatus(OrderStatus.CREATED);
        order.setNotes("New payment done, waiting processing.");

        String newPaymentKey = paymentServiceClient.requestPayment(order);
        order.setPaymentKey(newPaymentKey);
        orderRepository.save(order);
    }

    public Optional<Order> loadOrder(Long id){
        return orderRepository.findById(id);
    }

    private void sendPaymentRequest(Order order) {
        var paymentKey = paymentServiceClient.requestPayment(order);
        order.setPaymentKey(paymentKey);
    }

    private void persistOrder(Order order) {
        orderRepository.save(order);
        orderItemRepository.saveAll(order.getItems());
    }
}
