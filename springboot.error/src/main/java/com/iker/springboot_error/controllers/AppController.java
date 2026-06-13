package com.iker.springboot_error.controllers;

import com.iker.springboot_error.exceptions.UserNotFoundException;
import com.iker.springboot_error.models.domain.User;
import com.iker.springboot_error.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    private UserService service;

    @GetMapping
    public String index(){
        int value = Integer.parseInt("10");
        return "OK 200";
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<?> show(@PathVariable(name = "id") Long id){
        //User user = service.findById(id).orElseThrow(() -> new UserNotFoundException("Error el usuario no existe!"));
        Optional<User> optionalUser = service.findById(id);
        if(optionalUser.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optionalUser.get());
    }

}
