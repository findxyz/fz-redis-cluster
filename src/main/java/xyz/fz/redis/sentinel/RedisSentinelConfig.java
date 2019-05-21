package xyz.fz.redis.sentinel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.util.List;

@Profile("sentinel")
@Configuration
public class RedisSentinelConfig {
    @Value("${spring.redis.sentinel.nodes}")
    private List<String> nodes;

    @Value("${spring.redis.sentinel.master}")
    private String master;

    @Value("${spring.redis.password}")
    private String password;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration().master(master);
        for (String node : nodes) {
            String[] sentinel = node.split(":");
            sentinelConfig = sentinelConfig.sentinel(sentinel[0], Integer.parseInt(sentinel[1]));
        }
        sentinelConfig.setDatabase(0);
        sentinelConfig.setPassword(password);
        return new LettuceConnectionFactory(sentinelConfig);
    }
}
