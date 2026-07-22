package br.dev.fornarilabs.ecommerce.orders.domain.exception;

public class ItemNotFoundException extends RuntimeException{
    public ItemNotFoundException(String message){
        super(message);
    }
}
