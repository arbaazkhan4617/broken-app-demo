package com.demo;

import jakarta.validation.constraints.NotNull;

public class PaymentRequest {
    private String username;
    private double amount;
    @NotNull
    private String currency;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
