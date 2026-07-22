package br.dev.fornarilabs.ecommerce.products.service;

import br.dev.fornarilabs.ecommerce.products.domain.Product;
import br.dev.fornarilabs.ecommerce.products.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public Product save(Product product){
        return repository.save(product);
    }

    public Optional<Product> findById(Long id){
        return repository.findById(id);
    }
}
