package com.example.modular.products;

import com.example.modular.orders.OrderPlacedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class Product {

    ProductCollaborator productCollaborator;

    Product(ProductCollaborator productCollaborator) {
        this.productCollaborator = productCollaborator;
    }

//    @EventListener
    @Async
    @ApplicationModuleListener
    void on(OrderPlacedEvent event) throws Exception {
        System.out.println("starting: " + event);
        Thread.sleep(5_000);
        this.productCollaborator.takeOut(event.order());
        System.out.println("stopping");
    }

}

@Component
class ProductCollaborator {
    int takeOut(int id) {
        System.out.println("take out product : " + id);
        return id + id;
    }
}
