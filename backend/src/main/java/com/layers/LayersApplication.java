package com.layers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.security.autoconfigure.SecurityAutoConfiguration;
import org.springframework.boot.security.autoconfigure.UserDetailsServiceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, UserDetailsServiceAutoConfiguration.class})
@EnableJpaRepositories(basePackages = "com.layers.repository.postgres")
@EnableMongoRepositories(basePackages = "com.layers.repository.mongo")
public class LayersApplication {

    public static void main(String[] args) {
        SpringApplication.run(LayersApplication.class, args);
    }

}