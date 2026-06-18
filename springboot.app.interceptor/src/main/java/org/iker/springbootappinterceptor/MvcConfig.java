package org.iker.springbootappinterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Archivo para configurar los interceptors
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    // Archivo de interceptors (LoadingTimeInterceptor)
    @Autowired
    @Qualifier("loadingTimeInterceptor")
    private HandlerInterceptor timeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Agregamos el archivo de interceptors
        //registry.addInterceptor(timeInterceptor); // Se ejecutan en todas las rutas
        registry.addInterceptor(timeInterceptor).addPathPatterns("/app/bar", "/app/foo"); // Solo se ejecuta el interceptor en las rutas agregadas
        registry.addInterceptor(timeInterceptor).excludePathPatterns("/app/baz"); // Se excluyen las rutas agregadas
    }
}
