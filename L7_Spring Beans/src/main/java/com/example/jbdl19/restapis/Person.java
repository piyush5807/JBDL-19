package com.example.jbdl19.restapis;

import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Setter
@Getter
@AllArgsConstructor
@ToString
@Builder
public class Person {

    private static Logger log = LoggerFactory.getLogger(Person.class);

    private String email;
    private String name;
    private double amount;
    private String DNI;

    public Person() {
        log.info("Creating person object - {}", this);
    }

    public static void main(String[] args) {

        Person person = Person.builder()
                .amount(2.6)
                .name("ABC")
                .build();

        log.info("person = {}", person);
    }
}
