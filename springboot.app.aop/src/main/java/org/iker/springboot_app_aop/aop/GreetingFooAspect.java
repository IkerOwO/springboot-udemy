package org.iker.springboot_app_aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.util.Arrays;

// Aspects ordenados con @Order
// Se ejecuta primero este Aspect
@Order(1)
@Aspect
@Component
public class GreetingFooAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());



    @Before("GreetingServicePointCut.greetingFooLoggerPointCut()")
    private void loggerBefore(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("Antes Primero: {} invocados con los argumentos: {}", method, args);
    }

    @After("GreetingServicePointCut.greetingFooLoggerPointCut()")
    private void loggerAfter(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("Despues Primero: {} invocado con los parametros: {}", method, args);
    }

}
