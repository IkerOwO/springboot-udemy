package org.iker.springboot_di_factura;

import org.iker.springboot_di_factura.models.Item;
import org.iker.springboot_di_factura.models.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Arrays;
import java.util.List;

@Configuration
// Ponemos el archivo de config
@PropertySource(value = "classpath:data.properties")
public class AppConfig {

    // @Primary
    @Bean
    List<Item> itemsInvoice() {
        Product p1 = new Product("Teclado Razer", 160);
        Product p2 = new Product("Raton Logitech", 88);
        return Arrays.asList(
                new Item(p1, 2),
                new Item(p2, 4)
        );
    }

    @Bean
    List<Item> itemsInvoiceOffice() {
        Product p1 = new Product("Teclado Logitech", 22);
        Product p2 = new Product("Monitor Acer 100Hz", 99);
        Product p3 = new Product("Reposa muñecas teclado", 15);
        return Arrays.asList(
                new Item(p1, 10),
                new Item(p2, 25),
                new Item(p3, 1)
        );
    }
}
