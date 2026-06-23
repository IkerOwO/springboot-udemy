package org.iker.springboot_calendar_interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
// Registramos el Interceptor
public class MvcConfig implements WebMvcConfigurer {

    // Inyectamos el Interceptor
    @Autowired
    @Qualifier("calendarInterceptor")
    private HandlerInterceptor calendar;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Agregamos todas las rutas con /**
        registry.addInterceptor(calendar).addPathPatterns("/**");
    }
}
