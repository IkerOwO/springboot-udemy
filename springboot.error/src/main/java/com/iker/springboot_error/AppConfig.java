package com.iker.springboot_error;

import com.iker.springboot_error.models.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfig {
    @Bean
    List<User> users(){
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "Pepe", "Ruiz"));
        users.add(new User(2L, "Juan", "Rodriguez"));
        users.add(new User(3L, "Maria", "Ramirez"));
        return users;
    }
}
