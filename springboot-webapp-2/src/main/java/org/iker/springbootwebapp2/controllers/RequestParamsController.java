package org.iker.springbootwebapp2.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.iker.springbootwebapp2.models.dto.ParamDto;
import org.iker.springbootwebapp2.models.dto.ParamMixDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
* PARA PASAR LOS PARAMETROS MEDIANTE LA URL
*/
@RestController
@RequestMapping("/api/params")
public class RequestParamsController {

    @GetMapping("/foo")
    // @RequestParam espera una parametro en la URL -> '/api/params/foo?message=Hola'
    public ParamDto foo(@RequestParam(required = false, defaultValue = "Hola que tal") String message){
        ParamDto param = new ParamDto();
        // Si el mensaje es distinto de null se envia el mensaje, si no se envia "Hola"
        param.setMessage(message != null? message:"Hola");
        return param;
    }

    @GetMapping("/bar")
    // Varios params (No se pueden enviar Objects)
    public ParamMixDto bar(@RequestParam(required = false) String text, @RequestParam(required = false) Integer code){
        ParamMixDto params = new ParamMixDto();
        params.setMessage(text);
        params.setCode(code);
        return params;
    }

    // Inyeccion con HttpServletRequest en vez de @RequestParam
    @GetMapping("/request")
    public ParamMixDto request(HttpServletRequest request){
        // Valor por defecto
        int code = 0;
        try {
            code = Integer.parseInt(request.getParameter("code"));
        } catch (NumberFormatException e){
        }
        ParamMixDto params = new ParamMixDto();
        params.setCode(code);
        params.setMessage(request.getParameter("message"));

        return params;
    }
}
