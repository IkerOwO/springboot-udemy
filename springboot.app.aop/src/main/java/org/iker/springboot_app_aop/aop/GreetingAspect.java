package org.iker.springboot_app_aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.util.Arrays;

// Se ejecuta segundo este Aspect
@Order(2)
@Aspect
@Component
public class GreetingAspect {

    // Logger para ejecutar eventos
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // Registro de Logger antes de la llamada
    // Usamos JoinPoint para unir la ejecución de un aspecto
    // Ponemos el punto de corte en @Before (ponemos "execution()" y dentro de los parentesis
    // lo que se devuelve en el metodo, el nombre de la clase/interfaz.nombreMetodo(..))
    // Se puede poner un asterisco si queremos que devuelva cualquier cosa "execution(* org.iker.springboot_app_aop.services.GreetingService.sayHello(..))"
    // Tambien se puede crear en otro archivo (QUE ES LO RECOMENDADO) para poder reutilizar el codigo y hacerlo de manera limpia
    @Before("GreetingServicePointCut.greetingLoggerPointCut()")
    private void loggerBefore(JoinPoint joinPoint) {
        // Obtenemos el nombre del metodo y los argumentos del metodo que queremos pasar
        String method = joinPoint.getSignature().getName();
        // .getArgs nos da un array (el cual tenemos que convertir a String en este caso)
        String args = Arrays.toString(joinPoint.getArgs());
        // Concatenacion con {}
        logger.info("Antes: {} con los argumentos: {}", method, args);
    }

    // Se invoca despues de la ejecucion
    @After("GreetingServicePointCut.greetingLoggerPointCut()")
    private void loggerAfter(JoinPoint joinPoint) {
        // Obtenemos el nombre del metodo y los argumentos del metodo que queremos pasar
        String method = joinPoint.getSignature().getName();
        // .getArgs nos da un array (el cual tenemos que convertir a String en este caso)
        String args = Arrays.toString(joinPoint.getArgs());
        // Concatenacion con {}
        logger.info("Despues: {} con los argumentos: {}", method, args);
    }

    // Despues de retornar algo
    @AfterReturning("GreetingServicePointCut.greetingLoggerPointCut()")
    private void loggerAfterReturning(JoinPoint joinPoint) {
        // Obtenemos el nombre del metodo y los argumentos del metodo que queremos pasar
        String method = joinPoint.getSignature().getName();
        // .getArgs nos da un array (el cual tenemos que convertir a String en este caso)
        String args = Arrays.toString(joinPoint.getArgs());
        // Concatenacion con {}
        logger.info("Despues de retornar: {} con los argumentos: {}", method, args);
    }

    // Se intercepta cuando se lanza una excepcion
    @AfterThrowing("GreetingServicePointCut.greetingLoggerPointCut()")
    private void loggerAfterThrowing(JoinPoint joinPoint) {
        // Obtenemos el nombre del metodo y los argumentos del metodo que queremos pasar
        String method = joinPoint.getSignature().getName();
        // .getArgs nos da un array (el cual tenemos que convertir a String en este caso)
        String args = Arrays.toString(joinPoint.getArgs());
        // Concatenacion con {}
        logger.info("Despues de lanzar excepcion: {} con los argumentos: {}", method, args);
    }

    // Advice Around

    // ProceedingJoinPoint se usa mientras se esta ejecutando,
    // a diferencia de JoinPoint que se usa cuando ya se ha acabado la ejecucion
    @Around("GreetingServicePointCut.greetingLoggerPointCut()")
    public Object loggerAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String method = proceedingJoinPoint.getSignature().getName();

        String args = Arrays.toString(proceedingJoinPoint.getArgs());

        Object result = null;

        try {
            logger.info("El metodo: {} con los argumentos: {}", method, args);
            result = proceedingJoinPoint.proceed();
            logger.info("El metodo: {} retorna los resultados: {}", method, result);
            return result;
        } catch (Throwable e) {
            logger.error("Error en la llamada del metodo {}", method);
            throw e;
        }
    }
}
