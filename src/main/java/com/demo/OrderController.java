package com.demo;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class OrderController {

    private final PaymentService paymentService;

    public OrderController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/pay")
    public String submitPayment(@Valid @RequestBody PaymentRequest request) {
        // Explicitly check for a negative amount before calling the payment service.
        // This prevents the PaymentService from throwing IllegalArgumentException.
        if (request.getAmount() < 0) {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }
        return paymentService.process(request);
    }
}