package lambda.work.javaorders.services;

import lambda.work.javaorders.models.Order;

public interface OrderServices {
    Order save(Order order);

    Order getOrderById(long id);
}
