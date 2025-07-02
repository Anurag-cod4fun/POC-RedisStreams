package com.example.demo.stream;
import java.util.Map;

import org.redisson.api.RStream;
import org.redisson.api.RedissonClient;
import org.redisson.api.StreamMessageId;
import org.redisson.api.stream.StreamAddArgs;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserCreatedEvent;

@Service
public class RedisStreamPublisher {

    private static final String STREAM_NAME = "user_created_stream";

    private final RedissonClient redissonClient;

    public RedisStreamPublisher(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    public void publishUserCreated(UserCreatedEvent event) {
//        RStream<String, Object> stream = redissonClient.getStream(STREAM_NAME);
//
//        StreamAddArgs<String, Object> args = StreamAddArgs.entry("userId", event);
    	
    	RStream<String, String> stream = redissonClient.getStream(STREAM_NAME);

    	StreamAddArgs<String, String> args = StreamAddArgs.entries(
    	    "userId", event.getUserId(),
    	    "email", event.getEmail(),
    	    "name", event.getName()
    	);

//    	stream.add(StreamMessageId.AUTO_GENERATED, args);

        
        StreamMessageId entryId = stream.add(args); // XADD
        System.out.println("🟢 Event published to stream with ID: " + entryId);
    }
}
