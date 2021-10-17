package com.example.jbdl19.demoredis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {


    private static final String PERSON_KEY_PREFIX = "person::";

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @PostMapping("/set_value")
    public void setValue(@RequestBody Person person){

        // store this person in a key named as person::person.getId();

        String key = PERSON_KEY_PREFIX + person.getId();

        redisTemplate.opsForValue().set(key, person);
    }

    @GetMapping("/get_value")
    public Person getValue(@RequestParam("id") long id){
        String key = PERSON_KEY_PREFIX + id;
        return (Person) redisTemplate.opsForValue().get(key);
    }
}
