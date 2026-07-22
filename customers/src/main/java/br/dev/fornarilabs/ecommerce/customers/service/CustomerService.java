package br.dev.fornarilabs.ecommerce.customers.service;

import br.dev.fornarilabs.ecommerce.customers.domain.Customer;
import br.dev.fornarilabs.ecommerce.customers.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;

    public Customer save(Customer customer){
        return repository.save(customer);
    }

    public Optional<Customer> findById(Long id){
        return repository.findById(id);
    }
}
