package com.example.jbdl19.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class TestController {

    private HashMap<Integer, Person> personHashMap = new HashMap<>();

    // We cannot have 2 same APIs - same method and same endpoint

    @GetMapping("/person")
    public Person getPerson(@RequestParam("id") int id){
        return personHashMap.get(id);
    }

    @PostMapping("/person")
    public String insertPerson(@RequestBody Person person){
        personHashMap.put(person.getId(), person);
        return personHashMap.containsKey(person.getId()) ?
                "Data saved successfully":
                "Data could not be saved" ;
    }
}


