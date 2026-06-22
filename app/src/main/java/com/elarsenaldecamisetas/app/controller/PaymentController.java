package com.elarsenaldecamisetas.app.controller;

import com.elarsenaldecamisetas.app.dto.payment.CaptureRequest;
import com.elarsenaldecamisetas.app.dto.payment.CaptureResponse;
import com.elarsenaldecamisetas.app.dto.payment.PaymentRequest;
import com.elarsenaldecamisetas.app.dto.payment.PaymentResponse;
import com.elarsenaldecamisetas.app.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<PaymentResponse> createOrder(@Valid @RequestBody PaymentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.createOrder(request));
    }

    @PostMapping("/capture")
    public ResponseEntity<CaptureResponse> captureOrder(@Valid @RequestBody CaptureRequest request) {
        return ResponseEntity.ok(paymentService.captureOrder(request));
    }
}
