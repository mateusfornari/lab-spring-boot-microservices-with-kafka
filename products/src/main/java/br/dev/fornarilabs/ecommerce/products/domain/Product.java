package br.dev.fornarilabs.ecommerce.products.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "unit_amount", precision = 16, scale = 2, nullable = false)
    private BigDecimal unitAmount;
}
