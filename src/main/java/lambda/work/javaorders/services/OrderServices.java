package lambda.work.javaorders.services;

import lambda.work.javaorders.models.Order;

import java.util.List;

public interface OrderServices {
    Order save(Order order);

    Order getOrderById(long id);

    List<Order> getOrdersWithAdvance();
}
