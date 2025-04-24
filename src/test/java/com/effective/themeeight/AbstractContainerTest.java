package com.effective.themeeight;

import com.redis.testcontainers.RedisContainer;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;

public abstract class AbstractContainerTest {

    protected static final RedisContainer REDIS_CONTAINER;

    protected static final PostgreSQLContainer POSTGRES_CONTAINER;
    private static final Network NETWORK = Network.builder()
            .createNetworkCmdModifier(cmd -> cmd.withName("testcontainers-net"))
            .build();

    static {
        POSTGRES_CONTAINER = new PostgreSQLContainer("postgres:13.4");
        POSTGRES_CONTAINER.withNetwork(NETWORK)
                .start();
        REDIS_CONTAINER = new RedisContainer("redis:7.2.0");
        REDIS_CONTAINER.withNetwork(NETWORK)
                .start();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", POSTGRES_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRES_CONTAINER::getUsername);
        registry.add("spring.datasource.password", POSTGRES_CONTAINER::getPassword);
        registry.add("spring.data.redis.host", REDIS_CONTAINER::getHost);
        registry.add("spring.data.redis.port", () -> REDIS_CONTAINER.getMappedPort(6379));
    }
}
