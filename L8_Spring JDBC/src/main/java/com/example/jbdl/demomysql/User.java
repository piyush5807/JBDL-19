package com.example.jbdl.demomysql;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    int id;
    String name;
    String country;
    int age;
}
