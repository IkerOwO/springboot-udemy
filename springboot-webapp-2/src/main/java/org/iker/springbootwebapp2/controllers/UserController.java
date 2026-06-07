package org.iker.springbootwebapp2.controllers;

import org.iker.springbootwebapp2.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

// Los controllers manejan metodos handler (Peticiones de los usuarios)
@Controller
@RequestMapping("/api")
public class UserController {

    @GetMapping("/detailsThymeleaf")
    // Retornar el nombre de la plantilla de Thymeleaf creada en /resources/templates
    public String details(Model model){
        User user = new User("Iker", "Erdociain");
        user.setEmail("iker@gmail.com");

        // Pasar datos a la vista con Model (Tambien se puede pasar con un Map<>)
        model.addAttribute("title", "Hola mundo Spring Boot");
        model.addAttribute("user", user);
        return "details";
    }

    @GetMapping("/listThymeleaf")
    public String list(Model model){
        model.addAttribute("title", "Listado de usuarios");
        return "list";
    }

    // Mandar los usuarios sin el Model, ES GLOBAL
    @ModelAttribute("users")
    public List<User> usersModel() {
        return Arrays.asList(
                new User("Pepa", "Gonzalez"),
                new User("Lalo", "Perez", "lalo@gmail.com"),
                new User("Juanita", "Roe"),
                new User("Pepe", "Gonzalez", "pepe@gmail.com")
        );
    }
}
