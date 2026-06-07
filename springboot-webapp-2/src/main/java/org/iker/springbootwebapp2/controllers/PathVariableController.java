package org.iker.springbootwebapp2.controllers;

import org.iker.springbootwebapp2.models.User;
import org.iker.springbootwebapp2.models.dto.ParamDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/var")
public class PathVariableController {

    // Pasar elementos de config mediante @Value, el config.x se encuentra en values.properties
    @Value("${config.code}")
    private int code;

    @Value("${config.username}")
    private String username;

    @Value("${config.message}")
    private String message;

    @Value("${config.listOfValues}")
    private List<String> listOfValues;

    // Trabajar con codigo Java
    @Value("#{'${config.listOfValues}'.toUpperCase().split(',')}")
    private List<String> valueList;

    @Value("#{'${config.listOfValues}'.toUpperCase()}")
    private String valueString;

    @Value("#{${config.valuesMap}}")
    private Map<String, Object> valuesMap;

    @Value("#{${config.valuesMap}.product}")
    private String product;

    @Value("#{${config.valuesMap}.price}")
    private long price;

    // Pasar elementos con Environment y @Autowired
    @Autowired
    private Environment environment;

    // Pasamos los parametros mediante {message}
    @GetMapping("/baz/{message}")
    // Se puede poner el atributo (name = "message") 'name' tiene que contener el mismo nombre que lleva entre llaves
    public ParamDto baz(@PathVariable String message){
        ParamDto param = new ParamDto();
        param.setMessage(message);
        return param;
    }

    // Obtener varios parametros de la ruta (localhost:8080/api/var/mix/Teclado/123)
    @GetMapping("/mix/{product}/{id}")
    public Map<String, Object> mixPathvar(@PathVariable String product, @PathVariable Long id){
        Map<String, Object> json = new HashMap<>();
        json.put("product", product);
        json.put("id", id);
        return json;
    }

    // Testear API con POST con Postman
    @PostMapping("/create")
    public User create(@RequestBody User user){
        // Hacer algo con el usuario 'de ejemplo'
        user.setName(user.getName().toUpperCase());
        return user;
    }

    // Pasar los @Values (Podemos inyectar uno nuevo en los parametros de la funcion)
    @GetMapping("/values")
    public Map<String, Object> values(@Value("${config.cat}") String cat){
        Map<String, Object> json = new HashMap<>();
        json.put("Code", code);
        json.put("Username", username);
        json.put("Message", message);
        // Con Environment
        json.put("Message2", environment.getProperty("config.message"));
        json.put("Code2", environment.getProperty("config.code", Integer.class)); // Para que cambie de String a int
        json.put("cat", cat);
        json.put("List of values", listOfValues);
        json.put("Value List", valueList);
        json.put("Value String", valueString);
        json.put("Values Map", valuesMap);
        json.put("Product", product);
        json.put("Price", price);
        return json;
    }
}
