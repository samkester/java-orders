package lambda.work.javaorders.services;

import lambda.work.javaorders.models.Customer;
import lambda.work.javaorders.models.Order;
import lambda.work.javaorders.models.Payment;
import lambda.work.javaorders.repositories.CustomerRepository;
import lambda.work.javaorders.repositories.OrderRepository;
import lambda.work.javaorders.repositories.PaymentRepository;
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
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Transactional
    @Override
    public Order save(Order order) {
        Order newOrder = new Order();

        if(order.getOrdnum() != 0)
        {
            orderRepository.findById(order.getOrdnum()).orElseThrow(() -> new EntityNotFoundException("Could not find order with id '" + order.getOrdnum() + "' to update."));
            newOrder.setOrdnum(order.getOrdnum());
        }
        newOrder.setOrdamount(order.getOrdamount());
        newOrder.setAdvanceamount(order.getAdvanceamount());
        newOrder.setOrderdescription(order.getOrderdescription());

        Customer customer = customerRepository.findById(order.getCustomer().getCustcode()).orElseThrow(() -> new EntityNotFoundException("Could not find customer with id '" + order.getCustomer().getCustcode() + "' for new/updated order."));
        newOrder.setCustomer(customer);

        for(Payment p : order.getPayments())
        {
            Payment np = paymentRepository.findById(p.getPaymentid()).orElseThrow(() -> new EntityNotFoundException("Could not find payment with id '" + p.getPaymentid() + "' for new/updated order."));
            newOrder.addPayments(np);
        }

        return orderRepository.save(newOrder);
    }

    @Override
    public List<Order> getOrdersWithAdvance() {
        List<Order> result = orderRepository.findByAdvanceamountGreaterThan(0);
        return result;
    }

    @Override
    public Order getOrderById(long id) {
        Order result = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order with ID '" + id + "' does not exist."));
        return result;
    }

    @Override
    public void delete(long id) {
        orderRepository.deleteById(id);
    }
}
