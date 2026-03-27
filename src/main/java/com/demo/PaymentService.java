package com.demo;

public class PaymentService {
    public void process(User user, double amount) {
        System.out.println("Processing payment of $" + amount);
        
        if (user != null && user.name != null && user.name.length() > 0) {
            System.out.println("User is valid: " + user.name);
        }
        
        // ... rest of payment logic
    }
}

class User {
    public String name;
}