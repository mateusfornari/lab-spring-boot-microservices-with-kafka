package br.dev.fornarilabs.ecommerce.orders.repository;

import br.dev.fornarilabs.ecommerce.orders.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByIdAndPaymentKey(Long id, String paymentKey);
}
