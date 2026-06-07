package org.iker.spingbootapp1.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// Va a ser REST API
@RestController
public class HelloController {

    // Metodo para el GET
    @GetMapping("/api")
    // Al ser REST se va a retornar como JSON
    public Map<String, String> HelloWorld(){
        Map<String, String> json = new HashMap<>();
        json.put("Mensaje", "Hola mundo! API REST");
        json.put("Date", new Date().toString());
        return json;
    }

}
