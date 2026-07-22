package br.dev.fornarilabs.ecommerce.orders.controller;

import br.dev.fornarilabs.ecommerce.orders.controller.dto.CreateOrderDTO;
import br.dev.fornarilabs.ecommerce.orders.controller.dto.ErrorResponseDTO;
import br.dev.fornarilabs.ecommerce.orders.controller.dto.PaymentDataDTO;
import br.dev.fornarilabs.ecommerce.orders.domain.Order;
import br.dev.fornarilabs.ecommerce.orders.domain.exception.ItemNotFoundException;
import br.dev.fornarilabs.ecommerce.orders.domain.exception.ValidationException;
import br.dev.fornarilabs.ecommerce.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateOrderDTO body){
        try {
            Order order = body.toOrder();
            Order createdOrder = service.create(order);
            return ResponseEntity.ok(createdOrder.getId());
        }catch (ValidationException e){
            var error = new ErrorResponseDTO("Validation error", e.getField(), e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PostMapping("/{id}/payments")
    public ResponseEntity<?> addNewPayment(@PathVariable("id") Long orderId, @RequestBody PaymentDataDTO body){
        try {
            service.addPayment(orderId, body.data(), body.paymentType());
            return ResponseEntity.ok().build();
        }catch (ItemNotFoundException e){
            var error = new ErrorResponseDTO("Order not found", "id", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

}
