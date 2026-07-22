package br.dev.fornarilabs.ecommerce.orders.domain;


import br.dev.fornarilabs.ecommerce.orders.domain.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_orders")
@Getter
@Setter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "order_time", nullable = false)
    private LocalDateTime orderTime;

    @Column(name = "totalAmount", precision = 16, scale = 2)
    private BigDecimal totalAmount;

    @Column(name = "payment_key")
    private String paymentKey;

    @Column(name = "notes")
    private String notes;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "tracking_code")
    private String trackingCode;

    @Column(name = "nf_url")
    private String nfUrl;

    @Transient
    private PaymentData paymentData;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> items;
}
