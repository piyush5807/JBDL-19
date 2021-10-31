package com.example.jbdl.libraryapp.repositories;

import com.example.jbdl.libraryapp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
public class UserCacheRepository {

    private static String PREFIX_KEY = "usr::";

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    public User fetchUserFromCache(String username){
        return (User) redisTemplate.opsForValue().get(getKey(username));
    }

    public void saveUserInCache(User user){
        redisTemplate.opsForValue().set(getKey(user.getUsername()), user, Duration.ofHours(1));
    }

    private String getKey(String username){
        return PREFIX_KEY + username;
    }
}
