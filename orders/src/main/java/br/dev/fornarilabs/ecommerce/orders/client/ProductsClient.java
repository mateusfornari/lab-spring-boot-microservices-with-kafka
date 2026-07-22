package br.dev.fornarilabs.ecommerce.orders.client;

import br.dev.fornarilabs.ecommerce.orders.client.representation.ProductRepresentation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "products", url = "${ecommerce.orders.clients.products.url}")
public interface ProductsClient {

    @GetMapping("/{id}")
    ResponseEntity<ProductRepresentation> loadProduct(@PathVariable("id") Long id);
}
