package com.demo;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    public String process(PaymentRequest request) {
        String username = request.getUsername();
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (request.getAmount() < 0) { // Changed '>' to '<' to correctly validate for negative amounts
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        return "Payment of $" + request.getAmount() + " processed for " + username;
    }
}