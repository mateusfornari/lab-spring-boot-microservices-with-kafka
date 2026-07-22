package br.dev.fornarilabs.ecommerce.orders.client;

import br.dev.fornarilabs.ecommerce.orders.client.representation.CustomerRepresentation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customers", url = "${ecommerce.orders.clients.customers.url}")
public interface CustomersClient {

    @GetMapping("/{id}")
    ResponseEntity<CustomerRepresentation> loadCustomer(@PathVariable("id") Long id);
}
