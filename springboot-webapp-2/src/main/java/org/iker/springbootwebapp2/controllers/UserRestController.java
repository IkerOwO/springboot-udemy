package org.iker.springbootwebapp2.controllers;

import org.iker.springbootwebapp2.models.User;
import org.iker.springbootwebapp2.models.dto.UserDto;
import org.springframework.web.bind.annotation.*;

import java.util.*;

// Controlador para API REST
@RestController // Esto es igual que la combinacion de @Controller con @ResponseBody
@RequestMapping("/api") // Ruta de primer nivel
public class UserRestController {
    // Lo devuelve como JSON, siempre toma un nombre y un body (valor)

    //@RequestMapping(path = "/details", method = RequestMethod.GET) // Podemos usar esto en vez de @GetMapping
    @GetMapping("/details") // Ruta secundaria del metodo
    // Con DTO
    public UserDto details() {
        User user = new User("Iker", "Erdociain");
        UserDto userDto = new UserDto();

        userDto.setUser(user);
        userDto.setTitle("Hola Mundo!");

        return userDto;
    }

    @GetMapping("/list")
    public List<User> list() {
        User user = new User("Iker", "Erdociain");
        User user2 = new User("Pepe", "Gutierrez");
        User user3 = new User("John", "Doe");

        List<User> users = Arrays.asList(user, user2, user3);
        /*
        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user2);
        users.add(user3);
         */
        return users;
    }

    /*
    // Forma sin DTO
    public Map<String, Object> details(){
        User user = new User("Iker", "Erdociain");
        Map<String, Object> body = new HashMap<>();
        body.put("title", "Hola mundo!");
        body.put("user", user);
        return body;
    }
     */
}
