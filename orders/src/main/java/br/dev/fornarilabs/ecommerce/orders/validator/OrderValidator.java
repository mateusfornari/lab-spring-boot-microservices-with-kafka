package br.dev.fornarilabs.ecommerce.orders.validator;

import br.dev.fornarilabs.ecommerce.orders.client.CustomersClient;
import br.dev.fornarilabs.ecommerce.orders.client.ProductsClient;
import br.dev.fornarilabs.ecommerce.orders.client.representation.CustomerRepresentation;
import br.dev.fornarilabs.ecommerce.orders.client.representation.ProductRepresentation;
import br.dev.fornarilabs.ecommerce.orders.domain.Order;
import br.dev.fornarilabs.ecommerce.orders.domain.OrderItem;
import br.dev.fornarilabs.ecommerce.orders.domain.exception.ValidationException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderValidator {

    private final ProductsClient productsClient;
    private final CustomersClient customersClient;

    public void validate(Order order){
        validateCustomer(order.getCustomerId());
        order.getItems().forEach(this::validateItem);
    }

    private void validateCustomer(Long customerId){
        try{
            var response = customersClient.loadCustomer(customerId);
            CustomerRepresentation customer = response.getBody();
            log.info("Customer with ID {} found: {}", customer.id(), customer.name());
        }catch (FeignException.NotFound e){
            var message = String.format("Customer not found by ID: %d", customerId);
            throw new ValidationException("customerId", message);
        }
    }

    private void validateItem(OrderItem item){
        try{
            var response = productsClient.loadProduct(item.getProductId());
            ProductRepresentation product = response.getBody();
            log.info("Product with ID {} found: {}", product.id(), product.name());
        }catch (FeignException.NotFound e){
            var message = String.format("Product not found by ID: %d", item.getProductId());
            throw new ValidationException("productId", message);
        }
    }
}
