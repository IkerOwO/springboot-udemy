package org.iker.springbootappinterceptor.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import tools.jackson.databind.ObjectMapper;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

// Todos los interceptors deben de implementar 'HandlerInterceptor'
@Component("loadingTimeInterceptor")
public class LoadingTimeInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoadingTimeInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Cast para el method
        HandlerMethod controller = ((HandlerMethod) handler);
        // Imprimir algo
        logger.info("LoadingTimeInterceptor: preHandle() entrando..." + controller.getMethod().getName());

        // Calcular el tiempo transcurrido
        long start = System.currentTimeMillis();
        // Guardamos la var start en el request ya que ambos metodos lo contienen
        request.setAttribute("start", start);

        // Simular delay
        Random random = new Random();
        int delay = random.nextInt(500);
        Thread.sleep(delay);

        // Si retornase False hariamos esto
        /*
        Map<String, String> json = new HashMap<>();
        json.put("error", "no tienes acceso a esta pagina");
        json.put("date", new Date().toString());

        // Convertimos el Map en String
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(json);

        // Le pasamos el tipo de contenido
        response.setContentType("application/json");
        response.setStatus(403);

        // Pasamos el jsonString a la response
        response.getWriter().write(jsonString);

        return false;
         */
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        // Calcular tiempo final
        long end = System.currentTimeMillis();
        // Recuperamos la var start desde el request
        long start = (long) request.getAttribute("start");
        long result = end = start;
        logger.info("Tiempo transcurrido: "+ result + "ms!");

        // Imprimir algo
        logger.info("LoadingTimeInterceptor: postHandle() saliendo..." + ((HandlerMethod) handler).getMethod().getName());
    }
}
