package lambda.work.javaorders.repositories;

import lambda.work.javaorders.models.Customer;
import lambda.work.javaorders.views.CustomerOrderCount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    List<Customer> findByCustnameContainingIgnoringCase(String substring);

    @Query(value =  "SELECT c.custname name, count(ordnum) count " +
                    "FROM customers c LEFT JOIN orders o " +
                    "ON c.custcode = o.custcode " +
                    "GROUP BY c.custname " +
                    "ORDER BY count DESC", nativeQuery = true)
    List<CustomerOrderCount> findCustomerOrderCounts();
}
