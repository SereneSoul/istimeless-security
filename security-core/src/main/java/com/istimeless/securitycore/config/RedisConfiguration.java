package com.istimeless.securitycore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author lijiayin
 */
@Configuration
public class RedisConfiguration {
    
    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        stringRedisTemplate.setDefaultSerializer(stringRedisSerializer);
        stringRedisTemplate.setConnectionFactory(redisConnectionFactory);
        return stringRedisTemplate;
    }
}
