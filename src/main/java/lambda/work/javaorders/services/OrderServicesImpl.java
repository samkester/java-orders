package lambda.work.javaorders.services;

import lambda.work.javaorders.models.Agent;
import lambda.work.javaorders.models.Order;
import lambda.work.javaorders.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Transactional
@Service(value = "orderService")
public class OrderServicesImpl implements OrderServices {
    @Autowired
    OrderRepository orderRepository;

    @Transactional
    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrdersWithAdvance() {
        return null;
    }

    @Override
    public Order getOrderById(long id) {
        Order result = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order with ID '" + id + "' does not exist."));
        return result;
    }
}
