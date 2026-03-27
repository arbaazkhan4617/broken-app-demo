package com.demo;

public class PaymentService {
    public void process(User user, double amount) {
        System.out.println("Processing payment of $" + amount);
        
        // Check if user or user.name is null to prevent NullPointerException
        if (user == null || user.name == null) {
            System.out.println("Invalid user or user name.");
            return;
        }
        
        if (user.name.length() > 0) {
            System.out.println("User is valid: " + user.name);
        }
        
        // ... rest of payment logic
    }
}

class User {
    public String name;
}