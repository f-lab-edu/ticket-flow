package github.ticketflow.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redisson() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://my-cache-server:6379")
                .setConnectionMinimumIdleSize(10)
                .setConnectionPoolSize(10);

        return Redisson.create(config);
    }

}
