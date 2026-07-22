package br.dev.fornarilabs.ecommerce.customers.repository;

import br.dev.fornarilabs.ecommerce.customers.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
