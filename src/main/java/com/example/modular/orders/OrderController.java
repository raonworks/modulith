package com.example.modular.orders;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/orders")
class OrderController {

    private final OrderService orderService;

    OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    void placeOrder(@RequestBody Order order) {
        //TODO
        orderService.placeOrder(order);
    }
}

@Service
@Transactional
class OrderService {

    private final OrderRepository orderRepository;
    private final ApplicationEventPublisher publisher;

    OrderService(OrderRepository orderRepository, ApplicationEventPublisher publisher) {
        this.orderRepository = orderRepository;
        this.publisher = publisher;
    }

    void placeOrder(Order order) {
        Order ordered = orderRepository.save(order);
        System.out.println("save order: " + order);

        ordered.lineItems()
                .stream()
                .map(item -> new OrderPlacedEvent(ordered.id(), item.product(), item.quantity()))
                .forEach(publisher::publishEvent);
    }
}

interface OrderRepository extends CrudRepository<Order, Integer> {}

@Table("orders")
record Order(@Id Integer id, Set<LineItem> lineItems) {}

@Table("orders_line_items")
record LineItem(@Id Integer id, Integer product, Integer quantity) {}
