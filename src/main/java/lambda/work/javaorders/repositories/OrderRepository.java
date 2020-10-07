package lambda.work.javaorders.repositories;

import lambda.work.javaorders.models.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findByAdvanceamountGreaterThan(double minValue);
}
