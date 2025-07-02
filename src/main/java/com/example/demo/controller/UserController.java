package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserCreatedEvent;
import com.example.demo.stream.RedisStreamPublisher;

@RestController
@RequestMapping("/users")
public class UserController {

    private final RedisStreamPublisher streamPublisher;

    public UserController(RedisStreamPublisher streamPublisher) {
        this.streamPublisher = streamPublisher;
    }

    @PostMapping
    public String createUser(@RequestBody UserCreatedEvent userEvent) {
        // Save user logic here...

        streamPublisher.publishUserCreated(userEvent);
        return "User created and event published to stream.";
    }
}