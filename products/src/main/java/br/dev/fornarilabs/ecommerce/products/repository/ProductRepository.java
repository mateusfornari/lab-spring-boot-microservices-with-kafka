package br.dev.fornarilabs.ecommerce.products.repository;

import br.dev.fornarilabs.ecommerce.products.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
