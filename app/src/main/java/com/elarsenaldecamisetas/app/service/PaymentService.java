package com.elarsenaldecamisetas.app.service;

import com.elarsenaldecamisetas.app.dto.payment.PaymentRequest;
import com.elarsenaldecamisetas.app.dto.payment.PaymentResponse;
import com.elarsenaldecamisetas.app.dto.payment.CaptureRequest;
import com.elarsenaldecamisetas.app.dto.payment.CaptureResponse;

public interface PaymentService {
    PaymentResponse createOrder(PaymentRequest request);
    CaptureResponse captureOrder(CaptureRequest request);
}
