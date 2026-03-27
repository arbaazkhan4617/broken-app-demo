package com.demo;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    public String process(PaymentRequest request) {
        // INTENTIONAL BUG: If username is null, calling .length() throws a
        // NullPointerException
        // ResolvAI will detect this stack trace in the logs and automatically patch it.
        if (request.getUsername().length() == 0) {
            throw new IllegalArgumentException("Username cannot be empty");
        }

        return "Payment of $" + request.getAmount() + " processed for " + request.getUsername();
    }
}
