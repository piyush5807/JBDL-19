package com.example;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class Controller {

    @RequestMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        OAuth2User userPrincipal = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> answer = new HashMap<>();
        answer.put("my_name", principal.getAttribute("name"));
        answer.put("my_company", principal.getAttribute("company"));
        answer.put("my_location", principal.getAttribute("location"));
        return answer;
//		return Collections.singletonMap("name", principal.getAttribute("name"));
    }

    @RequestMapping("/user_details")
    public OAuth2User getUserDetails(@AuthenticationPrincipal OAuth2User principal){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return principal;
    }

    @GetMapping("/dummy")
    public String dummyUrl(){
        return "Hey! you have been logged out";
    }
}
