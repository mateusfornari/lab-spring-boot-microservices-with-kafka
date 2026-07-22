package br.dev.fornarilabs.ecommerce.orders.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "br.dev.fornarilabs.ecommerce.orders.client")
public class ClientsConfig {
}
