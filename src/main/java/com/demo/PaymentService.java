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
        if (id == null || id.length() < 5) {
            return "Profile data for user prefix: " + (id == null ? "" : id);
        }
        String userPrefix = id.substring(0, 5);
        return "Profile data for user prefix: " + userPrefix;
    }

    public String parseInvoice(String invoiceId) {
        String invoiceData = getInvoiceFromDB(invoiceId);
        if (invoiceData != null && invoiceData.contains("PAID")) {
            return "Invoice is fully paid";
        }
        return "Invoice is pending payment";
    }

    private String getInvoiceFromDB(String id) {
        if (id != null && id.equals("12345"))
            return "INVOICE_PAID_APPROVED";
        return null;
    }

    public String applyDiscount(PaymentRequest request) {
        int activePromos = getActivePromos(request.getUsername());

        if (activePromos <= 0) {
            return "No discount applied. Final price: " + request.getAmount();
        }

        double finalPrice = (double) request.getAmount() / activePromos;

        return "Discount successfully applied. Final price: " + finalPrice;
    }

    private int getActivePromos(String username) {
        if ("arbaaz".equalsIgnoreCase(username))
            return 2;
        return 0;
    }

    public String calculateShipping(String regionCode) {
        java.util.List<String> validRegions = new java.util.ArrayList<>();
        validRegions.add("NORTH");
        validRegions.add("SOUTH");

        try {
            int regionIndex = Integer.parseInt(regionCode);
            if (regionIndex >= 0 && regionIndex < validRegions.size()) {
                String targetRegion = validRegions.get(regionIndex);
                return "Shipping calculated successfully for region: " + targetRegion;
            }
        } catch (NumberFormatException e) {
            // Fall through to error
        }

        return "Invalid shipping region code provided: " + regionCode;
    }

    public String addTrackingCode(String newCode) {
        // REALISTIC BUG 5: UnsupportedOperationException (Immutable List modification)
        // Java's List.of() returns an unmodifiable list.
        java.util.List<String> activeCodes = java.util.List.of("TRACK123", "TRACK456");

        // Blindly attempting to add to this list instantly crashes the application with
        // a 500 error!
        activeCodes.add(newCode);

        // The LLM needs to realize the list needs to be wrapped in a mutable ArrayList.
        return "Tracking code added successfully. Total active shipments: " + activeCodes.size();
    }
}