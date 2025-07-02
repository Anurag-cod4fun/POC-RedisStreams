package com.example.demo.dto;
import java.io.Serializable;

public class UserCreatedEvent implements Serializable {
    private String userId;
    private String email;
    private String name;

    // Constructors
    public UserCreatedEvent() {}

    public UserCreatedEvent(String userId, String email, String name) {
        this.userId = userId;
        this.email = email;
        this.name = name;
    }

    // Getters and Setters
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
