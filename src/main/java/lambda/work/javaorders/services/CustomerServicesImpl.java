package lambda.work.javaorders.services;

import lambda.work.javaorders.models.Customer;
import lambda.work.javaorders.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
