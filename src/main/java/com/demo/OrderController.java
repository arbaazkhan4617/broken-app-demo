package com.demo;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
        return paymentService.process(request);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIllegalArgumentException(IllegalArgumentException ex) {
        return ex.getMessage();
    }

    @GetMapping("/user/{id}")
    public String getUserProfile(@PathVariable String id) {
        return paymentService.fetchUserProfile(id);
    }

    @GetMapping("/invoice/{invoiceId}")
    public String fetchInvoice(@PathVariable String invoiceId) {
        return paymentService.parseInvoice(invoiceId);
    }

    @PostMapping("/discount")
    public String applyDiscount(@RequestBody PaymentRequest request) {
        return paymentService.applyDiscount(request);
    }

    @GetMapping("/shipping/{regionCode}")
    public String calculateShipping(@PathVariable String regionCode) {
        return paymentService.calculateShipping(regionCode);
    }

    @PostMapping("/tracking/{newCode}")
    public String addTrackingCode(@PathVariable String newCode) {
        return paymentService.addTrackingCode(newCode);
    }
}