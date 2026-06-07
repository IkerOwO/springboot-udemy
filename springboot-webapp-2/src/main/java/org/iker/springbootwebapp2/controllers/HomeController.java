package org.iker.springbootwebapp2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Con cualquiera de estas rutas, va a este metodo
    @GetMapping({"", "/", "/home"})
    public String home(){
        // Reedirigir a la pagina /list
        return "redirect:/listThymeleaf";
        
        // No se pierden parametros con el 'forward' ni cambia la URL
        //return "forward:/listThymeleaf";
    }
}
