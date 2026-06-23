package org.iker.springboot_app_aop.controllers;

import org.iker.springboot_app_aop.services.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class GreetingController {

    @Autowired
    private GreetingService greetingService;

    @GetMapping("/greeting")
    public ResponseEntity<?> greeting(){
        return ResponseEntity.ok(
                Collections.singletonMap("greetings", greetingService.sayHello("Pepe", "Hola que tal!"))
        );
    }

    @GetMapping("/greeting-error")
    public ResponseEntity<?> greetingError(){
        return ResponseEntity.ok(
                Collections.singletonMap("greetings", greetingService.sayHelloError("Juan", "Hola que tal!"))
        );
    }
}
