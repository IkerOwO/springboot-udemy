package org.iker.springboot_di_factura.models;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class Invoice {

    @Autowired
    private Clients clients;

    @Value("${invoice.description}")
    private String description;

    @Autowired
    @Qualifier("itemsInvoiceOffice") // Indicar que lista de items queremos usar (podemos usar @Primary encima de la funcion en AppConfig)
    private List<Item> items;

    @PostConstruct // La diferencia entre esto y un constructor vacio es que el @PostContruct coge los datos ya procesados y se pueden imprimir, mientras que con un constructor normal se imprimiria 'null'
    public void init(){
        System.out.println("Creando el componente de la factura");
        clients.setName(clients.getName().concat(" ").concat(clients.getLastname()));
    }

    @PreDestroy // Se realiza una tarea antes de destruir el componente @bean
    public void destroy(){
        System.out.println("Desstruyendo el componenete o bean 'invoice'");
    }

    public Clients getClients() {
        return clients;
    }

    public void setClients(Clients clients) {
        this.clients = clients;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getTotal(){
        //int total = 0;

        //for(Item item :  items){
         //   total += item.getImport();
        //}

        int total = items.stream()
                .map(item -> item.getImport()) // Convertimos a int
                .reduce(0, (sum, importe) -> sum + importe); // Sumamos al importe por cada item
        return total;
    }
}
