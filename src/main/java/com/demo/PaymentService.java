package com.demo;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    public String process(PaymentRequest request) {
        String username = request.getUsername();
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (request.getAmount() < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }

        return "Payment of $" + request.getAmount() + " processed for " + username;
    }

    public String fetchUserProfile(String id) {
        // REALISTIC BUG 1: StringIndexOutOfBoundsException
        // If id length is less than 5, this crashes with a 500 error!
        String userPrefix = id.substring(0, 5);
        return "Profile data for user prefix: " + userPrefix;
    }

    public String parseInvoice(String invoiceId) {
        // REALISTIC BUG 2: NullPointerException
        String invoiceData = getInvoiceFromDB(invoiceId);
        // If invoiceData is null, calling .contains() throws an NPE!
        if (invoiceData.contains("PAID")) {
            return "Invoice is fully paid";
        }
        return "Invoice is pending payment";
    }

    private String getInvoiceFromDB(String id) {
        if (id.equals("12345"))
            return "INVOICE_PAID_APPROVED";
        // Simulate a database returning null for an unknown invoice
        return null;
    }

    public String applyDiscount(PaymentRequest request) {
        // REALISTIC BUG 3: ArithmeticException (Divide by zero)
        int activePromos = getActivePromos(request.getUsername());

        // If the user has 0 promos, this throws a Divide by Zero 500 error!
        double finalPrice = request.getAmount() / activePromos;

        return "Discount successfully applied. Final price: " + finalPrice;
    }

    private int getActivePromos(String username) {
        if ("arbaaz".equalsIgnoreCase(username))
            return 2;
        return 0; // 0 active promos by default
    }
}