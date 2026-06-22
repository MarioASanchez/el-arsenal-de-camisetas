package com.elarsenaldecamisetas.app.service.impl;

import java.io.IOException;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.stereotype.Service;

import com.elarsenaldecamisetas.app.dto.payment.CaptureRequest;
import com.elarsenaldecamisetas.app.dto.payment.CaptureResponse;
import com.elarsenaldecamisetas.app.dto.payment.PaymentRequest;
import com.elarsenaldecamisetas.app.dto.payment.PaymentResponse;
import com.elarsenaldecamisetas.app.service.PaymentService;
import com.paypal.core.PayPalHttpClient;
import com.paypal.orders.AmountWithBreakdown;
import com.paypal.orders.ApplicationContext;
import com.paypal.orders.LinkDescription;
import com.paypal.orders.Order;
import com.paypal.orders.OrderRequest;
import com.paypal.orders.OrdersCaptureRequest;
import com.paypal.orders.OrdersCreateRequest;
import com.paypal.orders.PurchaseUnitRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PayPalHttpClient payPalHttpClient;

    @Override
    public PaymentResponse createOrder(PaymentRequest request) {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.checkoutPaymentIntent("CAPTURE");

        ApplicationContext applicationContext = new ApplicationContext()
                .brandName("El Arsenal de Camisetas")
                .landingPage("NO_PREFERENCE")
                .userAction("PAY_NOW")
                .returnUrl("http://localhost:3000/payment/success")
                .cancelUrl("http://localhost:3000/payment/cancel");
        orderRequest.applicationContext(applicationContext);

        PurchaseUnitRequest purchaseUnit = new PurchaseUnitRequest()
                .referenceId(request.getOrderId().toString())
                .amountWithBreakdown(new AmountWithBreakdown()
                        .currencyCode(request.getCurrency())
                        .value(request.getTotal().setScale(2, RoundingMode.HALF_UP).toString()));

        orderRequest.purchaseUnits(List.of(purchaseUnit));

        OrdersCreateRequest createRequest = new OrdersCreateRequest().requestBody(orderRequest);

        try {
            Order order = payPalHttpClient.execute(createRequest).result();
            String orderId = order.id();
            String approvalUrl = order.links().stream()
                    .filter(link -> "approve".equals(link.rel()))
                    .findFirst()
                    .map(LinkDescription::href)
                    .orElseThrow(() -> new RuntimeException("Approval URL not found"));

            return new PaymentResponse(orderId, approvalUrl);
        } catch (IOException e) {
            throw new RuntimeException("Error creating PayPal order", e);
        }
    }

    @Override
    public CaptureResponse captureOrder(CaptureRequest request) {
        OrdersCaptureRequest captureRequest = new OrdersCaptureRequest(request.getPaypalOrderId());

        try {
            Order order = payPalHttpClient.execute(captureRequest).result();
            String status = order.status();
            boolean success = "COMPLETED".equals(status);
            return new CaptureResponse(success, status);
        } catch (IOException e) {
            throw new RuntimeException("Error capturing PayPal order", e);
        }
    }
}
