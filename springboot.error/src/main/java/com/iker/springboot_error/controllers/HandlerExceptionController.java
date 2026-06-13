package com.iker.springboot_error.controllers;

import com.iker.springboot_error.exceptions.UserNotFoundException;
import com.iker.springboot_error.models.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// Controlador para manejar las excepciones
@RestControllerAdvice
public class HandlerExceptionController {

    // Excepcion por si se divide entre 0
    @ExceptionHandler(ArithmeticException.class)
    // El ? se puede poner si no se sabe que se va a devolver (String, int...)
    public ResponseEntity<?> divisionByZero(Exception e){
        Error error = new Error();
        error.setDate(new Date());
        error.setError("Error división por 0");
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(error);
        //return ResponseEntity.internalServerError().body(error);
    }

    // Error 404
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Error> notFoundException(Exception e){
        Error error = new Error();
        error.setDate(new Date());
        error.setError("API rest no encontrada");
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // NumericFormat Exception
    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> numberFormatException(Exception e){
        Map<String, Object> error = new HashMap<>();
        error.put("date", new Date().toString());
        error.put("error", "Numero invalido o incorrecto, no tiene formato de digito");
        error.put("message", e.getMessage());
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());

        return error;
    }

    // NullPointer Exception
    @ExceptionHandler({NullPointerException.class,
            HttpMessageNotWritableException.class,
            UserNotFoundException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> userNotFoundException(Exception e){
        Map<String, Object> error = new HashMap<>();
        error.put("date", new Date().toString());
        error.put("error", "Usuario o Role no existe");
        error.put("message", e.getMessage());
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());

        return error;
    }

}
