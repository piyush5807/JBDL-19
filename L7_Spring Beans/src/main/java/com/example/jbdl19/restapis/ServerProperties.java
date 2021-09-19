package com.example.jbdl19.restapis;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("myapp")
@Component
@EnableConfigurationProperties
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ServerProperties {

    private int price;
    private String quantity;

}
