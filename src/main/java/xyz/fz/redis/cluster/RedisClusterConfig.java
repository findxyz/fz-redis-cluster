package xyz.fz.redis.cluster;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.util.List;

@Profile("cluster")
@Configuration
public class RedisClusterConfig {
    @Value("${spring.redis.cluster.nodes}")
    private List<String> nodes;

    @Value("${spring.redis.password}")
    private String password;

    @Bean
    public RedisConnectionFactory connectionFactory() {
        RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration(nodes);
        clusterConfiguration.setPassword(password);
        return new LettuceConnectionFactory(clusterConfiguration);
    }
}
