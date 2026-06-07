package org.iker.springbootwebapp2;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/*
    Archivo de configuracion de los archivos x.properties
 */
@Configuration
// Configurar el archivo 'values.properties'
@PropertySources({
        @PropertySource(value = "classpath:values.properties", encoding = "UTF-8"),
        // @PropertySource("classpath:values2.properties")
})
public class ValuesConfig {

}
