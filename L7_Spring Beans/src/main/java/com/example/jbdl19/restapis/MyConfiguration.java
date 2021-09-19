package com.example.jbdl19.restapis;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Scope("prototype") // even if singleton, it doesn't matter because bean level scope is considered
public class MyConfiguration {


    @Bean // ~ component annotation the only diff is it's method level and component is class level
    @Scope("prototype")
    public RestTemplate getTemplate(){
        System.out.println("In MyConfiguration class: getTemplate function");
        return new RestTemplate();
    }
}
