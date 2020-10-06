package lambda.work.javaorders.services;

import lambda.work.javaorders.models.Customer;
import lambda.work.javaorders.repositories.CustomerRepository;
import lambda.work.javaorders.views.CustomerOrderCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "customerService")
public class CustomerServicesImpl implements CustomerServices {
    @Autowired
    CustomerRepository customerRepository;

    @Transactional
    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getCustomers() {
        List<Customer> result = new ArrayList<>();
        customerRepository.findAll().iterator().forEachRemaining(result::add);
        return result;
    }

    @Override
    public Customer getCustomerById(long id) {
        Customer result = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer with ID '" + id + "' does not exist."));
        return result;
    }

    @Override
    public List<Customer> getCustomersByNameSubstring(String substring) {
        List<Customer> result = customerRepository.findByCustnameContainingIgnoringCase(substring);
        return result;
    }

    @Override
    public List<CustomerOrderCount> getCustomersByOrderCount() {
        List<CustomerOrderCount> result = customerRepository.findCustomerOrderCounts();
        return result;
    }
}
