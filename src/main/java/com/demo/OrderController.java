package com.demo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class OrderController {

    private final PaymentService paymentService;

    public OrderController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/pay")
    public String submitPayment(@RequestBody PaymentRequest request) {
        return paymentService.process(request);
    }
}
