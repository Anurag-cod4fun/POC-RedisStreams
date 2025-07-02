package com.example.demo.stream;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FwkRedisStreamConfig {
	String redisHost = "localhost";
	int redisPort = 6379;
	String redisPassword = ""; // leave empty if no auth
	int timeout = 3000;
	int poolSize = 64;
	int minIdleSize = 10;

	@Bean(destroyMethod = "shutdown")
	public RedissonClient redissonClient() {
		Config config = new Config();
		String redisUrl = "redis://" + redisHost + ":" + redisPort;

		SingleServerConfig singleServerConfig = config.useSingleServer().setAddress(redisUrl)
				.setConnectionPoolSize(poolSize).setConnectionMinimumIdleSize(minIdleSize).setTimeout(timeout);

		if (redisPassword != null && !redisPassword.isEmpty()) {
			singleServerConfig.setPassword(redisPassword);
		}

//		config.setThreads(8);
//		config.setNettyThreads(32);
		
		System.out.println("✅ Redisson connecting to: " + redisUrl);

		return Redisson.create(config);
	}
}
