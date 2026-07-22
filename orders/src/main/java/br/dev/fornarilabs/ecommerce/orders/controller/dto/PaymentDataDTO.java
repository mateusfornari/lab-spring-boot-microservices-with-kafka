package br.dev.fornarilabs.ecommerce.orders.controller.dto;

import br.dev.fornarilabs.ecommerce.orders.domain.PaymentData;
import br.dev.fornarilabs.ecommerce.orders.domain.enums.PaymentType;

public record PaymentDataDTO(
        String data,
        PaymentType paymentType
) {
    public PaymentData toPaymentData(){
        PaymentData paymentData = new PaymentData();
        paymentData.setData(data);
        paymentData.setPaymentType(paymentType);
        return paymentData;
    }
}
