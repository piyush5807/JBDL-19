package com.example.jbdl19.demoredis;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person implements Serializable {

    private long id;
    private String name;
    private double credit_score;
    private int age;
    private boolean seniorCitizen;
}
