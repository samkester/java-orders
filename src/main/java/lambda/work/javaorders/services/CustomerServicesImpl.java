package lambda.work.javaorders.services;

import lambda.work.javaorders.models.Agent;
import lambda.work.javaorders.models.Customer;
import lambda.work.javaorders.models.Order;
import lambda.work.javaorders.models.Payment;
import lambda.work.javaorders.repositories.AgentRepository;
import lambda.work.javaorders.repositories.CustomerRepository;
import lambda.work.javaorders.repositories.PaymentRepository;
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
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    AgentRepository agentRepository;

    @Transactional
    @Override
    public Customer save(Customer customer) {
        Customer newCustomer = new Customer();
        newCustomer.setCustname(customer.getCustname());
        newCustomer.setCustcity(customer.getCustcity());
        newCustomer.setWorkingarea(customer.getWorkingarea());
        newCustomer.setCustcountry(customer.getCustcountry());
        newCustomer.setGrade(customer.getGrade());
        newCustomer.setOpeningamt(customer.getOpeningamt());
        newCustomer.setReceiveamt(customer.getReceiveamt());
        newCustomer.setPaymentamt(customer.getPaymentamt());
        newCustomer.setOutstandingamt(customer.getOutstandingamt());
        newCustomer.setPhone(customer.getPhone());

        if(customer.getCustcode() != 0)
        {
            customerRepository.findById(customer.getCustcode()).orElseThrow(() -> new EntityNotFoundException("Could not find customer with id '" + customer.getCustcode() + "' to update."));
            newCustomer.setCustcode(customer.getCustcode());
        }

        Agent agent = agentRepository.findById(customer.getAgent().getAgentcode())
                .orElseThrow(() -> new EntityNotFoundException("Could not find agent with id '" + customer.getAgent().getAgentcode() + "' for new customer named '" + customer.getCustname() + "'."));

        newCustomer.setAgent(agent);

        agent.getCustomers().add(newCustomer);

        for (Order o : customer.getOrders())
        {
            Order nO = new Order(o.getOrdamount(), o.getAdvanceamount(), newCustomer, o.getOrderdescription());
            for (Payment p : o.getPayments())
            {
                Payment nP = paymentRepository.findById(p.getPaymentid())
                    .orElseThrow(() -> new EntityNotFoundException("Could not find payment with id '" + p.getPaymentid() + "' for new customer named '" + customer.getCustname() + "'."));
                nO.addPayments(nP);
            }
            newCustomer.getOrders().add(nO);
        }

        return customerRepository.save(newCustomer);
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

    @Override
    public Customer update(Customer customer, long id) {
        Customer newCustomer = customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Could not find customer with id '" + customer.getCustcode() + "' to update."));

        if(customer.getCustname() != null) newCustomer.setCustname(customer.getCustname());
        if(customer.getCustcity() != null) newCustomer.setCustcity(customer.getCustcity());
        if(customer.getWorkingarea() != null) newCustomer.setWorkingarea(customer.getWorkingarea());
        if(customer.getCustcountry() != null) newCustomer.setCustcountry(customer.getCustcountry());
        if(customer.getGrade() != null) newCustomer.setGrade(customer.getGrade());
        if(customer.hasvalueforopeningamt) newCustomer.setOpeningamt(customer.getOpeningamt());
        if(customer.hasvalueforrecieveamt) newCustomer.setReceiveamt(customer.getReceiveamt());
        if(customer.hasvalueforpaymentamt) newCustomer.setPaymentamt(customer.getPaymentamt());
        if(customer.hasvalueforoutstandingamt) newCustomer.setOutstandingamt(customer.getOutstandingamt());
        if(customer.getPhone() != null) newCustomer.setPhone(customer.getPhone());

        if(customer.getAgent() != null) {
            Agent agent = agentRepository.findById(customer.getAgent().getAgentcode())
                    .orElseThrow(() -> new EntityNotFoundException("Could not find agent with id '" + customer.getAgent().getAgentcode() + "' for updated customer named '" + customer.getCustname() + "'."));

            newCustomer.setAgent(agent);

            agent.getCustomers().add(newCustomer);
        }

        if(customer.hasvaluefororders) {
            newCustomer.getOrders().clear();
            for (Order o : customer.getOrders()) {
                Order nO = new Order(o.getOrdamount(), o.getAdvanceamount(), newCustomer, o.getOrderdescription());
                for (Payment p : o.getPayments()) {
                    Payment nP = paymentRepository.findById(p.getPaymentid())
                            .orElseThrow(() -> new EntityNotFoundException("Could not find payment with id '" + p.getPaymentid() + "' for updated customer named '" + customer.getCustname() + "'."));
                    nO.addPayments(nP);
                }
                newCustomer.getOrders().add(nO);
            }
        }

        return customerRepository.save(newCustomer);
    }

    @Override
    public void delete(long id) {
        customerRepository.deleteById(id);
    }
}
