package com.example.jbdl.major_project;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Properties;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user")
    public User getUserByEmail(@RequestParam("search_type") String searchType,
                               @RequestParam("search_value") String searchValue) throws Exception {

        UserVectorEnum vector = UserVectorEnum.valueOf(searchType);
        switch (vector){
            case ID:
                return userService.getUser(Integer.parseInt(searchValue));
            case EMAIL:
                return userService.getUserByEmail(searchValue);
            default:
                throw new Exception("Invalid search type found");
        }
    }

    @PostMapping("/user")
    public void createUser(@RequestBody User user) throws JsonProcessingException {
        userService.createUser(user);
    }

}
