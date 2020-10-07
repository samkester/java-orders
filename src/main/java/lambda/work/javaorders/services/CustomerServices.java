package lambda.work.javaorders.services;

import lambda.work.javaorders.models.Customer;
import lambda.work.javaorders.views.CustomerOrderCount;

import java.util.List;

public interface CustomerServices {
    Customer save(Customer customer);

    List<Customer> getCustomers();

    Customer getCustomerById(long id);

    List<Customer> getCustomersByNameSubstring(String substring);

    List<CustomerOrderCount> getCustomersByOrderCount();
}
