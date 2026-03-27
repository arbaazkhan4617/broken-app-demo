package com.demo;

public class PaymentService {
    public void process(User user, double amount) {
        System.out.println("Processing payment of $" + amount);
        
        // Fix: Check if user.name is not null before accessing its length
        if (user.name != null && user.name.length() > 0) {
            System.out.println("User is valid: " + user.name);
        } else {
            System.out.println("User name is null or empty.");
        }
        
        // ... rest of payment logic
    }
}

class User {
    public String name;
}