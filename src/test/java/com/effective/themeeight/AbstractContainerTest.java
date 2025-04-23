package com.effective.themeeight;

import com.redis.testcontainers.RedisContainer;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.Network;

public abstract class AbstractContainerTest {

    protected static final RedisContainer REDIS_CONTAINER;
    private static final Network NETWORK = Network.newNetwork();

    static {
        REDIS_CONTAINER = new RedisContainer("redis:7.2.0");
        REDIS_CONTAINER.start();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.redis.host", REDIS_CONTAINER::getHost);
        registry.add("spring.data.redis.port", () -> REDIS_CONTAINER.getMappedPort(6379));
    }
}
