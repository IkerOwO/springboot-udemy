package org.iker.springboot_app_aop.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GreetingServicePointCut {

    // Sirve para reutilizar y indicar el PointCut de forma mas ordenada
    @Pointcut("execution(String org.iker.springboot_app_aop.services.GreetingService.*(..))")
    public void greetingLoggerPointCut() {}

    @Pointcut("execution(String org.iker.springboot_app_aop.services.GreetingService.*(..))")
    public void greetingFooLoggerPointCut() {}
}
