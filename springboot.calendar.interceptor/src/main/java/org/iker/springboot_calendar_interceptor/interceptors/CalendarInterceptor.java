package org.iker.springboot_calendar_interceptor.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import tools.jackson.databind.ObjectMapper;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// Lo anotamos como @Component
@Component("calendarInterceptor")
// Tiene que implementar la interfaz 'HandlerInterceptor'
public class CalendarInterceptor implements HandlerInterceptor {

    // inyectamos las horas agregadas al application.properties
    @Value("${config.calendar.open}")
    private int open;

    @Value("${config.calendar.close}")
    private int close;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Obtenemos la hora actual
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        // Comparamos si la hora esta entra la hora de apertura y salida
        if(hour >= open && hour < close){
            // Usamos StringBuilder para concatenar
            StringBuilder message = new StringBuilder("Bienvenidos al horario de atencion a clientes!");
            message.append(", atendemos desde las ");
            message.append(open);
            message.append("hrs. ");
            message.append("hasta las ");
            message.append(close);
            message.append("hrs. ");
            // Lo pasamos como atributo al request
            request.setAttribute("message", message.toString());
            return true;
        }

        // Avisar al usuario de que esta fuera de la hora
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> data = new HashMap<>();
        data.put("message", "Cerrado, fuera del horario de atencion");
        data.put("date", new Date().toString());
        // Lo pasamos a JSON
        response.setContentType("application/json");
        response.setStatus(401);
        response.getWriter().write(objectMapper.writeValueAsString(data));
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {


    }
}
